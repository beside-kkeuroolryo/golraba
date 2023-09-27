package donggi.dev.kkeuroolryo.core.comment.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import donggi.dev.kkeuroolryo.IntegrationTest;
import donggi.dev.kkeuroolryo.core.comment.application.dto.CommentDto;
import donggi.dev.kkeuroolryo.core.comment.domain.Comment;
import donggi.dev.kkeuroolryo.core.comment.domain.CommentRepository;
import donggi.dev.kkeuroolryo.core.comment.domain.exception.CommentNotFoundException;
import donggi.dev.kkeuroolryo.core.comment.domain.exception.CommentUnauthorizedException;
import donggi.dev.kkeuroolryo.core.question.domain.Category;
import donggi.dev.kkeuroolryo.core.question.domain.Question;
import donggi.dev.kkeuroolryo.core.question.domain.QuestionRepository;
import donggi.dev.kkeuroolryo.core.question.domain.exception.QuestionNotFoundException;
import donggi.dev.kkeuroolryo.web.comment.dto.CommentDeleteCommand;
import donggi.dev.kkeuroolryo.web.comment.dto.CommentRegisterCommand;
import java.util.Optional;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("Comment 등록, 삭제 IntegrationTest")
@IntegrationTest
class CommentEditorTest {

    @Autowired
    CommentEditor commentEditor;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    QuestionRepository questionRepository;


    Question question;
    Comment comment;
    @BeforeEach
    void setUp() {
        questionRepository.deleteAll();
        commentRepository.deleteAll();

        question = questionRepository.save(new Question("질문본문", "선택지A", "선택지B", Category.SELF));
        comment = commentRepository.save(new Comment(question.getId(), "사용자이름", "비밀번호123", "댓글본문"));
    }

    @Nested
    @DisplayName("댓글 등록 요청이")
    class Describe_save {

        @Nested
        @DisplayName("정상적인 요청이면")
        class Context_with_valid_register_command {

            @ParameterizedTest
            @CsvSource({"사용자 이름1,댓글 본문1", "사용자 이름2,댓글 본문2", "사용자 이름3,댓글 본문3"})
            @DisplayName("저장소에 댓글을 저장하고 id가 포함된 객체를 반환한다.")
            void return_question_dto(String username, String content) {
                Long questionId = 1L;
                CommentRegisterCommand commentRegisterCommand = new CommentRegisterCommand(username, "패스워드123", content);

                CommentDto commentDto = commentEditor.save(questionId, commentRegisterCommand);

                SoftAssertions.assertSoftly(softly -> {
                    softly.assertThat(commentDto.getId()).isNotNull();
                    softly.assertThat(commentDto.getUsername()).isEqualTo(username);
                    softly.assertThat(commentDto.getContent()).isEqualTo(content);
                });
            }
        }
    }

    @Nested
    @DisplayName("댓글 삭제 요청이")
    class Describe_delete {

        @Nested
        @DisplayName("정상적이라면")
        class Context_with_valid_delete_command {

            @Test
            @DisplayName("저장소에서 댓글을 삭제한다.")
            void delete_comment() {
                String password = "비밀번호123";
                CommentDeleteCommand commentDeleteCommand = new CommentDeleteCommand(password);
                commentEditor.delete(question.getId(), comment.getId(), commentDeleteCommand.getPassword());

                Optional<Comment> deletedComment = commentRepository.findById(comment.getId());

                assertThat(deletedComment).isNotPresent();
            }
        }

        @Nested
        @DisplayName("정상적이지 않다면 (비밀번호가 일치하지 않은 경우)")
        class Context_with_invalid_delete_command {

            @Test
            @DisplayName("예외를 발생시킨다.")
            void throws_exception() {
                String unauthorizedPassword = "비밀번호486";

                assertThatThrownBy(() -> commentEditor.delete(question.getId(), comment.getId(), unauthorizedPassword))
                    .isInstanceOf(CommentUnauthorizedException.class);
            }
        }

        @Nested
        @DisplayName("정상적이지 않다면 (질문 id가 존재하지 않을 경우)")
        class Context_with_invalid_question_id {

            @Test
            @DisplayName("예외를 발생시킨다.")
            void throws_exception() {
                Long invalidQuestionId = -1L;

                assertThatThrownBy(() -> commentEditor.delete(invalidQuestionId, comment.getId(), comment.getPassword().getPassword()))
                    .isInstanceOf(QuestionNotFoundException.class);
            }
        }

        @Nested
        @DisplayName("정상적이지 않다면 (댓글 id가 존재하지 않을 경우)")
        class Context_with_invalid_comment_id {

            @Test
            @DisplayName("예외를 발생시킨다.")
            void throws_exception() {
                Long invalidCommentId = -1L;

                assertThatThrownBy(() -> commentEditor.delete(question.getId(), invalidCommentId, comment.getPassword().getPassword()))
                    .isInstanceOf(CommentNotFoundException.class);
            }
        }
    }
}
