package donggi.dev.kkeuroolryo.core.question.application;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import donggi.dev.kkeuroolryo.IntegrationTest;
import donggi.dev.kkeuroolryo.core.question.application.dto.QuestionDto;
import donggi.dev.kkeuroolryo.core.question.domain.Category;
import donggi.dev.kkeuroolryo.core.question.domain.Question;
import donggi.dev.kkeuroolryo.core.question.domain.QuestionRepository;
import donggi.dev.kkeuroolryo.core.question.domain.QuestionResult;
import donggi.dev.kkeuroolryo.core.question.domain.QuestionResultRepository;
import donggi.dev.kkeuroolryo.core.question.domain.exception.QuestionInvalidChoiceException;
import donggi.dev.kkeuroolryo.core.question.domain.exception.QuestionNotFoundException;
import donggi.dev.kkeuroolryo.web.question.dto.QuestionRegisterDto;
import donggi.dev.kkeuroolryo.web.question.dto.QuestionResultCommand;
import donggi.dev.kkeuroolryo.web.question.dto.QuestionResultCommand.ChoiceResult;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("질문 등록, 결과 저장 IntegrationTest")
@IntegrationTest
class QuestionEditorTest {

    @Autowired
    QuestionEditor questionEditor;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuestionResultRepository questionResultRepository;

    Question question;

    @BeforeEach
    void setUp() {
        question = questionRepository.save(new Question("질문 본문", "선택지A", "선택지B", Category.SELF));
        questionResultRepository.save(new QuestionResult(question));
    }

    @Nested
    @DisplayName("질문 등록 요청이")
    class Describe_save {

        @Nested
        @DisplayName("정상적인 요청이면")
        class Context_with_valid_register_command {

            @ParameterizedTest
            @CsvSource({"본문1,선택A,선택B,SELF", "본문2,선택A2,선택B2,COUPLE", "본문3,선택A3,선택B3,USERMADE"})
            @DisplayName("저장소에 질문을 저장하고 id가 포함된 객체를 반환한다.")
            void return_question_dto(String content, String choiceA, String choiceB, Category category) {
                QuestionRegisterDto questionRegisterDto = new QuestionRegisterDto(
                    content, choiceA, choiceB, category);

                QuestionDto questionDto = questionEditor.save(questionRegisterDto);

                SoftAssertions.assertSoftly(softly -> {
                    softly.assertThat(questionDto.getId()).isNotNull();
                    softly.assertThat(questionDto.getContent()).isEqualTo(content);
                    softly.assertThat(questionDto.getChoiceA()).isEqualTo(choiceA);
                    softly.assertThat(questionDto.getChoiceB()).isEqualTo(choiceB);
                });
            }
        }

        @Nested
        @DisplayName("카테고리가 USERMADE이면")
        class Context_with_valid_register_command2 {

            @ParameterizedTest
            @CsvSource({"본문1,선택A,선택B,USERMADE", "본문2,선택A2,선택B2,USERMADE", "본문3,선택A3,선택B3,USERMADE"})
            @DisplayName("저장소에 질문을 저장하고 active는 false로 저장된다.")
            void return_question_dto(String content, String choiceA, String choiceB, Category category) {
                QuestionRegisterDto questionRegisterDto = new QuestionRegisterDto(
                    content, choiceA, choiceB, category);

                QuestionDto questionDto = questionEditor.save(questionRegisterDto);

                SoftAssertions.assertSoftly(softly -> {
                    softly.assertThat(questionDto.getId()).isNotNull();
                    softly.assertThat(questionDto.getContent()).isEqualTo(content);
                    softly.assertThat(questionDto.isActive()).isFalse();
                    softly.assertThat(questionDto.getChoiceA()).isEqualTo(choiceA);
                    softly.assertThat(questionDto.getChoiceB()).isEqualTo(choiceB);
                });
            }
        }

        @Nested
        @DisplayName("카테고리가 SELF, COUPLE, FRIEND 이면")
        class Context_with_valid_register_command3 {

            @ParameterizedTest
            @CsvSource({"본문1,선택A,선택B,COUPLE", "본문2,선택A2,선택B2,FRIEND", "본문3,선택A3,선택B3,SELF"})
            @DisplayName("저장소에 질문을 저장하고 active는 true로 저장된다.")
            void return_question_dto(String content, String choiceA, String choiceB, Category category) {
                QuestionRegisterDto questionRegisterDto = new QuestionRegisterDto(
                    content, choiceA, choiceB, category);

                QuestionDto questionDto = questionEditor.save(questionRegisterDto);

                SoftAssertions.assertSoftly(softly -> {
                    softly.assertThat(questionDto.getId()).isNotNull();
                    softly.assertThat(questionDto.getContent()).isEqualTo(content);
                    softly.assertThat(questionDto.isActive()).isTrue();
                    softly.assertThat(questionDto.getChoiceA()).isEqualTo(choiceA);
                    softly.assertThat(questionDto.getChoiceB()).isEqualTo(choiceB);
                });
            }
        }
    }

    @Nested
    @DisplayName("질문 선택 결과 등록 요청이")
    class Describe_result {

        @Nested
        @DisplayName("정상적인 요청이면")
        class Context_with_valid_result_command {

            @Test
            @DisplayName("저장소에 질문 선택 결과를 저장하고 정상 상태코드를 반환한다.")
            void return_question_dto() {
                List<ChoiceResult> results = new ArrayList<>();
                results.add(new ChoiceResult(question.getId(), "a"));
                results.add(new ChoiceResult(question.getId(), "a"));
                results.add(new ChoiceResult(question.getId(), "b"));
                QuestionResultCommand resultCommand = new QuestionResultCommand(results);

                questionEditor.result(resultCommand);

                Question findQuestion = questionRepository.getById(question.getId());
                SoftAssertions.assertSoftly(softly -> {
                    softly.assertThat(findQuestion.getQuestionResult().getChoiceAResult()).isEqualTo(2);
                    softly.assertThat(findQuestion.getQuestionResult().getChoiceBResult()).isEqualTo(1);
                });
            }
        }

        @Nested
        @DisplayName("100명의 사용자가 동시에 정상적인 요청을 하면")
        class Context_with_valid_result_concurrency {

            @Test
            @DisplayName("저장소에 질문 선택 결과를 저장하고 정상 상태코드를 반환한다.")
            void return_question_dto() throws InterruptedException {
                List<ChoiceResult> results = new ArrayList<>();
                results.add(new ChoiceResult(question.getId(), "a"));
                results.add(new ChoiceResult(question.getId(), "a"));
                results.add(new ChoiceResult(question.getId(), "b"));
                QuestionResultCommand resultCommand = new QuestionResultCommand(results);

                int threadCount = 100;
                ExecutorService executorService = Executors.newFixedThreadPool(32);
                CountDownLatch countDownLatch = new CountDownLatch(threadCount);
                for (int i = 0; i < threadCount; i++) {
                    executorService.submit(() -> {
                        try {
                            questionEditor.result(resultCommand);
                        } finally {
                            countDownLatch.countDown();
                        }
                    });

                }
                countDownLatch.await();

                Question findQuestion = questionRepository.getById(question.getId());
                SoftAssertions.assertSoftly(softly -> {
                    softly.assertThat(findQuestion.getQuestionResult().getChoiceAResult()).isEqualTo(200);
                    softly.assertThat(findQuestion.getQuestionResult().getChoiceBResult()).isEqualTo(100);
                });
            }
        }

        @Nested
        @DisplayName("정상적이지 않은 요청이면 (유효하지 않은 question id)")
        class Context_with_invalid_result_command {

            @Test
            @DisplayName("예외를 발생시킨다.")
            void return_exception() {
                Long invalidQuestionId = -1L;
                List<ChoiceResult> results = new ArrayList<>();
                results.add(new ChoiceResult(invalidQuestionId, "a"));
                QuestionResultCommand resultCommand = new QuestionResultCommand(results);

                assertThatThrownBy(() -> questionEditor.result(resultCommand))
                    .isInstanceOf(QuestionNotFoundException.class);
            }
        }

        @Nested
        @DisplayName("정상적이지 않은 요청이면 (유효하지 않은 선택)")
        class Context_with_invalid_choice_result {

            @Test
            @DisplayName("예외를 발생시킨다.")
            void return_exception() {
                List<ChoiceResult> results = new ArrayList<>();
                results.add(new ChoiceResult(question.getId(), "a"));
                results.add(new ChoiceResult(question.getId(), "a"));
                results.add(new ChoiceResult(question.getId(), "c"));
                QuestionResultCommand resultCommand = new QuestionResultCommand(results);

                assertThatThrownBy(() -> questionEditor.result(resultCommand))
                    .isInstanceOf(QuestionInvalidChoiceException.class);
            }
        }
    }
}
