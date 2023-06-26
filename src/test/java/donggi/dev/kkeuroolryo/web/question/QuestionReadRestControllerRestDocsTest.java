package donggi.dev.kkeuroolryo.web.question;

import static io.restassured.RestAssured.given;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document;

import donggi.dev.kkeuroolryo.InitRestDocsTest;
import donggi.dev.kkeuroolryo.RestAssuredAndRestDocsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

@DisplayName("API 문서화 : Question Read")
@RestAssuredAndRestDocsTest
public class QuestionReadRestControllerRestDocsTest extends InitRestDocsTest {

    @Test
    @DisplayName("특정 카테고리를 선택하면 해당 카테고리 질문을 15개 반환하고 정상 상태코드를 반환한다.")
    void post_register() {
        given(this.spec)
            .filter(
                document("random-questions-read",
                    responseFields(
                        fieldWithPath("category").description("질문 카테고리").type(JsonFieldType.STRING),
                        fieldWithPath("questions[].id").description("질문 아이디").type(JsonFieldType.NUMBER),
                        fieldWithPath("questions[].content").description("질문 본문").type(JsonFieldType.STRING),
                        fieldWithPath("questions[].choiceA").description("선택지 A").type(JsonFieldType.STRING),
                        fieldWithPath("questions[].choiceB").description("선택지 B").type(JsonFieldType.STRING)
                    )
                )
            )
            .log().all()
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .header("Content-type", MediaType.APPLICATION_JSON_VALUE)
            .pathParam("category", "self")

        .when()
            .get("/api/golrabas/category/{category}")

        .then()
            .log().all()
            .statusCode(HttpStatus.OK.value());
    }
}
