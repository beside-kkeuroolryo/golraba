package donggi.dev.kkeuroolryo.web.question;

import static io.restassured.RestAssured.given;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document;

import donggi.dev.kkeuroolryo.InitRestDocsTest;
import donggi.dev.kkeuroolryo.RestAssuredAndRestDocsTest;
import dev.donggi.core.api.core.question.domain.Question;
import dev.donggi.core.api.core.question.domain.QuestionRepository;
import dev.donggi.core.api.core.question.domain.QuestionResult;
import dev.donggi.core.api.core.question.domain.QuestionResultRepository;
import dev.donggi.core.api.web.question.dto.QuestionRegisterCommand;
import dev.donggi.core.api.web.question.dto.QuestionResultCommand;
import dev.donggi.core.api.web.question.dto.QuestionResultCommand.ChoiceResult;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

@DisplayName("API 문서화 : 질문 등록, 질문 선택 저장")
@RestAssuredAndRestDocsTest
class QuestionRestControllerRestDocsTest extends InitRestDocsTest {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuestionResultRepository questionResultRepository;

    Question question;

    @BeforeEach
    void setUp() {
        question = questionRepository.save(new Question("self", "질문 본문16", "선택A 16", "선택B 16"));
        questionResultRepository.save(new QuestionResult(question));
    }
    @Test
    @DisplayName("사용자의 질문 등록 요청이 정상적인 경우 질문 생성 후 상태코드를 반환한다.")
    void question_register() {
        QuestionRegisterCommand questionRegisterCommand = new QuestionRegisterCommand("요청한 질문 본문", "선택 A", "선택 B");
        given(this.spec)
            .filter(
                document("question-register",
                    requestFields(
                        fieldWithPath("content").description("요청한 질문 본문").type(JsonFieldType.STRING),
                        fieldWithPath("choiceA").description("선택지 A").type(JsonFieldType.STRING),
                        fieldWithPath("choiceB").description("선택지 B").type(JsonFieldType.STRING)
                    ),
                    responseFields(
                        fieldWithPath("code").description("응답 코드").type(JsonFieldType.STRING),
                        fieldWithPath("message").description("응답 메세지").type(JsonFieldType.STRING),
                        fieldWithPath("data.id").description("질문 id").type(JsonFieldType.NUMBER),
                        fieldWithPath("data.content").description("요청한 질문 본문").type(JsonFieldType.STRING),
                        fieldWithPath("data.choiceA").description("선택지 A").type(JsonFieldType.STRING),
                        fieldWithPath("data.choiceB").description("선택지 B").type(JsonFieldType.STRING),
                        fieldWithPath("data.choiceAResult").description("선택지 A 선택된 횟수").type(JsonFieldType.NUMBER),
                        fieldWithPath("data.choiceBResult").description("선택지 B 선택된 횟수").type(JsonFieldType.NUMBER)
                    )
                )
            )
            .log().all()
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .header("Content-type", MediaType.APPLICATION_JSON_VALUE)
            .body(questionRegisterCommand)

        .when()
            .post("/api/golrabas/question")

        .then()
            .log().all()
            .statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("사용자의 선택 결과 저장 요청이 정상적인 경우 선택 결과를 저장 한 후 정상 상태코드를 반환한다.")
    void question_result() {
        List<ChoiceResult> results = setChoiceResultData();
        QuestionResultCommand resultCommand = new QuestionResultCommand(results);
        given(this.spec)
            .filter(
                document("question-result",
                    requestFields(
                        fieldWithPath("results[].questionId").description("질문 id").type(JsonFieldType.NUMBER),
                        fieldWithPath("results[].choice").description("사용자의 선택").type(JsonFieldType.STRING)
                    )
                )
            )
            .log().all()
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .header("Content-type", MediaType.APPLICATION_JSON_VALUE)
            .body(resultCommand)

        .when()
            .post("/api/golrabas/result")

        .then()
            .log().all()
            .statusCode(HttpStatus.OK.value());
    }

    private List<ChoiceResult> setChoiceResultData() {
        List<ChoiceResult> results = new ArrayList<>();
        results.add(new ChoiceResult(question.getId(), "a"));
        results.add(new ChoiceResult(question.getId(), "a"));
        results.add(new ChoiceResult(question.getId(), "a"));
        results.add(new ChoiceResult(question.getId(), "a"));
        results.add(new ChoiceResult(question.getId(), "b"));
        results.add(new ChoiceResult(question.getId(), "b"));
        return results;
    }
}
