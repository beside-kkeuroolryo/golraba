package donggi.dev.kkeuroolryo.core.question.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import donggi.dev.kkeuroolryo.UnitTest;
import donggi.dev.kkeuroolryo.core.question.domain.exception.QuestionInvalidCategoryException;
import donggi.dev.kkeuroolryo.core.question.domain.exception.QuestionInvalidChoiceException;
import donggi.dev.kkeuroolryo.core.question.domain.exception.QuestionInvalidContentException;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Question 도메인 UnitTest")
@UnitTest
class QuestionTest {

    @Nested
    @DisplayName("생성자에")
    class Describe_constructor {

        @Nested
        @DisplayName("유효한 question 생성자 값들이 주어진다면")
        class Context_with_valid_parameters {

            @ParameterizedTest
            @CsvSource(value = {"couple, 본문1, 선택A, 선택B", "self, 본문2, 선택A, 선택B"})
            @DisplayName("question 객체를 반환한다.")
            void return_question_object(String category, String content, String choiceA, String choiceB) {
                Question question = new Question(category, content, choiceA, choiceB);

                SoftAssertions.assertSoftly(softly -> {
                    softly.assertThat(question.getCategory()).isEqualTo(category);
                    softly.assertThat(question.getContent()).isEqualTo(content);
                    softly.assertThat(question.getChoiceA()).isEqualTo(choiceA);
                    softly.assertThat(question.getChoiceB()).isEqualTo(choiceB);
                });
            }
        }

        @Nested
        @DisplayName("유효하지 않은 카테고리가 주어지면")
        class Context_with_invalid_category {

            @ParameterizedTest
            @NullAndEmptySource
            @ValueSource(strings = {"  ", "\t", "\n"})
            @DisplayName("예외를 발생시킨다.")
            void throws_exception(String category) {
                assertThatThrownBy(() -> new Question(category, "정상적인 본문 내용", "정상적인 선택지 A", "정상적인 선택지 B"))
                    .isInstanceOf(QuestionInvalidCategoryException.class);
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
                assertThatThrownBy(() -> new Question("정상 카테고리", content, "정상적인 선택지 A", "정상적인 선택지 B"))
                    .isInstanceOf(QuestionInvalidContentException.class);
            }
        }

        @Nested
        @DisplayName("유효하지 않은 선택값이 주어지면")
        class Context_with_invalid_choice {

            @ParameterizedTest
            @NullAndEmptySource
            @ValueSource(strings = {"  ", "\t", "\n"})
            @DisplayName("예외를 발생시킨다.")
            void throws_exception(String choice) {
                assertThatThrownBy(() -> new Question("정상 카테고리", "정상적인 본문", choice, "정상적인 선택지 B"))
                    .isInstanceOf(QuestionInvalidChoiceException.class);
            }
        }
    }
}
