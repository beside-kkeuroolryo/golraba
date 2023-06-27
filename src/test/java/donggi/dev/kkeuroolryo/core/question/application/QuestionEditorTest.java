package donggi.dev.kkeuroolryo.core.question.application;

import donggi.dev.kkeuroolryo.IntegrationTest;
import donggi.dev.kkeuroolryo.core.question.application.dto.QuestionDto;
import donggi.dev.kkeuroolryo.core.question.domain.QuestionRepository;
import donggi.dev.kkeuroolryo.web.question.dto.QuestionRegisterCommand;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("Question 생성 IntegrationTest")
@IntegrationTest
class QuestionEditorTest {

    @Autowired
    QuestionEditor questionEditor;

    @Autowired
    QuestionRepository questionRepository;

    @Nested
    @DisplayName("질문 등록 요청이")
    class Describe_save {

        @Nested
        @DisplayName("정상적인 요청이면")
        class Context_with_valid_register_command {

            @ParameterizedTest
            @CsvSource({"본문1,선택A,선택B", "본문2,선택A2,선택B2", "본문3,선택A3,선택B3"})
            @DisplayName("저장소에 질문을 저장하고 id가 포함된 객체를 반환한다.")
            void return_question_dto(String content, String choiceA, String choiceB) {
                QuestionRegisterCommand questionRegisterCommand = new QuestionRegisterCommand(
                    content, choiceA, choiceB);

                QuestionDto questionDto = questionEditor.save(questionRegisterCommand);

                SoftAssertions.assertSoftly(softly -> {
                    softly.assertThat(questionDto.getId()).isNotNull();
                    softly.assertThat(questionDto.getContent()).isEqualTo(content);
                    softly.assertThat(questionDto.getChoiceA()).isEqualTo(choiceA);
                    softly.assertThat(questionDto.getChoiceB()).isEqualTo(choiceB);
                });
            }
        }
    }
}
