package donggi.dev.kkeuroolryo.core.question.domain;

import donggi.dev.kkeuroolryo.UnitTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
    }
}
