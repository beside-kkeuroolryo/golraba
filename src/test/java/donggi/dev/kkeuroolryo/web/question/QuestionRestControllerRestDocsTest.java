package donggi.dev.kkeuroolryo.web.question;

import static io.restassured.RestAssured.given;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document;

import donggi.dev.kkeuroolryo.InitRestDocsTest;
import donggi.dev.kkeuroolryo.RestAssuredAndRestDocsTest;
import donggi.dev.kkeuroolryo.web.question.dto.QuestionRegisterCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

@DisplayName("API 문서화 : 유저 골라바 질문 등록 요청")
@RestAssuredAndRestDocsTest
class QuestionRestControllerRestDocsTest extends InitRestDocsTest {

    @Test
    @DisplayName("사용자의 질문 등록 요청이 정상적인 경우 질문 생성 후 상태코드를 반환한다.")
    void post_register() {
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
                        fieldWithPath("id").description("골라바 질문 아이디").type(JsonFieldType.NUMBER),
                        fieldWithPath("content").description("요청한 질문 본문").type(JsonFieldType.STRING),
                        fieldWithPath("choiceA").description("선택지 A").type(JsonFieldType.STRING),
                        fieldWithPath("choiceB").description("선택지 B").type(JsonFieldType.STRING)
                    )
                )
            )
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .header("Content-type", MediaType.APPLICATION_JSON_VALUE)
            .body(questionRegisterCommand)

        .when()
            .post("/api/golrabas/question")

        .then()
            .statusCode(HttpStatus.CREATED.value());
    }
}
