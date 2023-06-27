package donggi.dev.kkeuroolryo.core.comment.application;

import donggi.dev.kkeuroolryo.IntegrationTest;
import donggi.dev.kkeuroolryo.core.comment.application.dto.CommentDto;
import donggi.dev.kkeuroolryo.core.comment.domain.CommentRepository;
import donggi.dev.kkeuroolryo.web.comment.dto.CommentRegisterCommand;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("Comment 등록 IntegrationTest")
@IntegrationTest
class CommentEditorTest {

    @Autowired
    CommentEditor commentEditor;

    @Autowired
    CommentRepository commentRepository;

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
                CommentRegisterCommand commentRegisterCommand = new CommentRegisterCommand(username, "패스워드", content);

                CommentDto commentDto = commentEditor.save(questionId, commentRegisterCommand);

                SoftAssertions.assertSoftly(softly -> {
                    softly.assertThat(commentDto.getId()).isNotNull();
                    softly.assertThat(commentDto.getUsername()).isEqualTo(username);
                    softly.assertThat(commentDto.getContent()).isEqualTo(content);
                });
            }
        }
    }
}
