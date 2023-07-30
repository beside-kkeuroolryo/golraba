package donggi.dev.kkeuroolryo.web.question;

import static io.restassured.RestAssured.given;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document;

import donggi.dev.kkeuroolryo.InitRestDocsTest;
import donggi.dev.kkeuroolryo.RestAssuredAndRestDocsTest;
import dev.donggi.core.api.core.question.domain.Question;
import dev.donggi.core.api.core.question.domain.QuestionRepository;
import dev.donggi.core.api.core.question.domain.QuestionResult;
import dev.donggi.core.api.core.question.domain.QuestionResultRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

@DisplayName("API 문서화 : 질문 조회")
@RestAssuredAndRestDocsTest
public class QuestionReadRestControllerRestDocsTest extends InitRestDocsTest {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuestionResultRepository questionResultRepository;

    Question question;

    @BeforeEach
    void setUp() {
        questionRepository.deleteAll();

        questionRepository.save(new Question("self", "질문 본문1", "선택A 1", "선택B 1"));
        questionRepository.save(new Question("self", "질문 본문2", "선택A 2", "선택B 2"));
        questionRepository.save(new Question("self", "질문 본문3", "선택A 3", "선택B 3"));
        questionRepository.save(new Question("self", "질문 본문4", "선택A 4", "선택B 4"));
        questionRepository.save(new Question("self", "질문 본문5", "선택A 5", "선택B 5"));
        questionRepository.save(new Question("self", "질문 본문6", "선택A 6", "선택B 6"));
        questionRepository.save(new Question("self", "질문 본문7", "선택A 7", "선택B 7"));
        questionRepository.save(new Question("self", "질문 본문8", "선택A 8", "선택B 8"));
        questionRepository.save(new Question("self", "질문 본문9", "선택A 9", "선택B 9"));
        questionRepository.save(new Question("self", "질문 본문10", "선택A 10", "선택B 10"));
        questionRepository.save(new Question("self", "질문 본문11", "선택A 11", "선택B 11"));
        questionRepository.save(new Question("self", "질문 본문12", "선택A 12", "선택B 12"));
        questionRepository.save(new Question("self", "질문 본문13", "선택A 13", "선택B 13"));
        questionRepository.save(new Question("self", "질문 본문14", "선택A 14", "선택B 14"));
        questionRepository.save(new Question("self", "질문 본문15", "선택A 15", "선택B 15"));
        question = questionRepository.save(new Question("self", "질문 본문16", "선택A 16", "선택B 16"));
        questionResultRepository.save(new QuestionResult(question));
    }

    @Test
    @DisplayName("특정 카테고리를 선택하면 해당 카테고리 질문 id List 를 반환하고 정상 상태코드를 반환한다.")
    void questions_read() {
        given(this.spec)
            .filter(
                document("random-questions-read",
                    pathParameters(parameterWithName("category").description("질문 카테고리")),
                    responseFields(
                        fieldWithPath("code").description("응답 코드").type(JsonFieldType.STRING),
                        fieldWithPath("message").description("응답 메세지").type(JsonFieldType.STRING),
                        fieldWithPath("data.category").description("질문 카테고리").type(JsonFieldType.STRING),
                        fieldWithPath("data.questionIds").description("질문 id 리스트").type(JsonFieldType.ARRAY)
                    )
                )
            )
            .log().all()
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .header("Content-type", MediaType.APPLICATION_JSON_VALUE)
            .pathParam("category", question.getCategory())

        .when()
            .get("/api/golrabas/category/{category}")

        .then()
            .log().all()
            .statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("유효하지 않은 카테고리로 요청하면 Bad Request 상태코드를 반환한다.")
    void questions_read_invalid_category() {
        String invalidCategory = "haha";
        given(this.spec)
            .filter(
                document("questions-read-invalid-category",
                    pathParameters(parameterWithName("category").description("질문 카테고리"))
                )
            )
            .log().all()
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .header("Content-type", MediaType.APPLICATION_JSON_VALUE)
            .pathParam("category", invalidCategory)

        .when()
            .get("/api/golrabas/category/{category}")

        .then()
            .log().all()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("특정 질문 조회 요청이 주어지면 해당 질문을 반환하고 정상 상태코드를 반환한다.")
    void question_read() {
        given(this.spec)
            .filter(
                document("question-read",
                    pathParameters(parameterWithName("questionId").description("질문 id")),
                    responseFields(
                        fieldWithPath("code").description("응답 코드").type(JsonFieldType.STRING),
                        fieldWithPath("message").description("응답 메세지").type(JsonFieldType.STRING),
                        fieldWithPath("data.id").description("질문 id").type(JsonFieldType.NUMBER),
                        fieldWithPath("data.content").description("질문 본문").type(JsonFieldType.STRING),
                        fieldWithPath("data.choiceA").description("선택지 A").type(JsonFieldType.STRING),
                        fieldWithPath("data.choiceB").description("선택지 B").type(JsonFieldType.STRING),
                        fieldWithPath("data.choiceAResult").description("선택지 A의 득표율").type(JsonFieldType.NUMBER),
                        fieldWithPath("data.choiceBResult").description("선택지 B의 득표율").type(JsonFieldType.NUMBER)
                    )
                )
            )
            .log().all()
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .header("Content-type", MediaType.APPLICATION_JSON_VALUE)
            .pathParam("questionId", question.getId())

        .when()
            .get("/api/golrabas/{questionId}", question.getId())

        .then()
            .log().all()
            .statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("유효하지 않은 질문 id 로 질문 조회 요청이 주어지면 Bad Request 상태코드를 반환한다.")
    void question_read_invalid_question_id() {
        Long invalidQuestionId = -1L;
        given(this.spec)
            .filter(
                document("question-read-invalid-question-id",
                    pathParameters(parameterWithName("questionId").description("질문 id"))
                )
            )
            .log().all()
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .header("Content-type", MediaType.APPLICATION_JSON_VALUE)
            .pathParam("questionId", invalidQuestionId)

        .when()
            .get("/api/golrabas/{questionId}", question.getId())

        .then()
            .log().all()
            .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}