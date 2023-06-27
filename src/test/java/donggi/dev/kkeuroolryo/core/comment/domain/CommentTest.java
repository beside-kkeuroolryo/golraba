package donggi.dev.kkeuroolryo.core.comment.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import donggi.dev.kkeuroolryo.UnitTest;
import donggi.dev.kkeuroolryo.core.comment.domain.exception.CommentInvalidContentException;
import donggi.dev.kkeuroolryo.core.comment.domain.exception.CommentInvalidPasswordException;
import donggi.dev.kkeuroolryo.core.comment.domain.exception.CommentInvalidUsernameException;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Comment 도메인 UnitTest")
@UnitTest
class CommentTest {

    @Nested
    @DisplayName("생성자에")
    class Describe_constructor {

        @Nested
        @DisplayName("유효한 comment 생성자 값들이 주어진다면")
        class Context_with_valid_parameters {

            @ParameterizedTest
            @CsvSource(value = {"1, 사용자이름1, 패스워드123, 댓글본문1", "2, 사용자이름2, 패스워드234, 댓글본문2"})
            @DisplayName("comment 객체를 반환한다.")
            void return_comment_object(Long questionId, String username, String password, String content) {
                Comment comment = new Comment(questionId, username, password, content);

                SoftAssertions.assertSoftly(softly -> {
                    softly.assertThat(comment.getQuestionId()).isEqualTo(questionId);
                    softly.assertThat(comment.getUsername().getUsername()).isEqualTo(username);
                    softly.assertThat(comment.getPassword().getPassword()).isEqualTo(password);
                    softly.assertThat(comment.getContent().getContent()).isEqualTo(content);
                });
            }
        }

        @Nested
        @DisplayName("유효하지 않은 본문 내용이 주어지면")
        class Context_with_invalid_content {

            @ParameterizedTest
            @NullAndEmptySource
            @ValueSource(strings = {"  ", "\t", "\n"})
            @DisplayName("예외를 발생시킨다.")
            void throws_exception(String content) {
                assertThatThrownBy(() -> new Comment(1L, "정상 사용자이름", "정상 패스워드", content))
                    .isInstanceOf(CommentInvalidContentException.class);
            }
        }

        @Nested
        @DisplayName("유효하지 않은 비밀번호가 주어지면")
        class Context_with_invalid_password {

            @ParameterizedTest
            @NullAndEmptySource
            @ValueSource(strings = {"  ", "\t", "\n"})
            @DisplayName("예외를 발생시킨다.")
            void throws_exception(String password) {
                assertThatThrownBy(() -> new Comment(1L, "정상 사용자이름", password, "정상 댓글본문"))
                    .isInstanceOf(CommentInvalidPasswordException.class);
            }
        }

        @Nested
        @DisplayName("최소 길이를 지키지 않은 비밀번호가 주어지면")
        class Context_with_minimum_password {

            @ParameterizedTest
            @ValueSource(strings = {"15323", "1234", "123", "dd", "d"})
            @DisplayName("예외를 발생시킨다.")
            void throws_exception(String password) {
                assertThatThrownBy(() -> new Comment(1L, "정상 사용자이름", password, "정상 댓글본문"))
                    .isInstanceOf(CommentInvalidPasswordException.class);
            }
        }

        @Nested
        @DisplayName("유효하지 않은 사용자 이름이 주어지면")
        class Context_with_invalid_username {

            @ParameterizedTest
            @NullAndEmptySource
            @ValueSource(strings = {"  ", "\t", "\n"})
            @DisplayName("예외를 발생시킨다.")
            void throws_exception(String username) {
                assertThatThrownBy(() -> new Comment(1L, username, "정상 패스워드", "정상 댓글본문"))
                    .isInstanceOf(CommentInvalidUsernameException.class);
            }
        }

        @Nested
        @DisplayName("최대 길이를 지키지 않은 사용자 이름이 주어지면")
        class Context_with_limit_username {

            @ParameterizedTest
            @ValueSource(strings = {"20자이상은안되어요01234567890"})
            @DisplayName("예외를 발생시킨다.")
            void throws_exception(String username) {
                assertThatThrownBy(() ->  new Comment(1L, username, "정상 패스워드", "정상 댓글본문"))
                    .isInstanceOf(CommentInvalidUsernameException.class);
            }
        }
    }
}
