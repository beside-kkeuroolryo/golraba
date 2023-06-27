package donggi.dev.kkeuroolryo.web.comment;

import static io.restassured.RestAssured.given;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document;

import donggi.dev.kkeuroolryo.InitRestDocsTest;
import donggi.dev.kkeuroolryo.RestAssuredAndRestDocsTest;
import donggi.dev.kkeuroolryo.web.comment.dto.CommentRegisterCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

@DisplayName("API 문서화 : 댓글 등록 요청")
@RestAssuredAndRestDocsTest
public class CommentRestControllerRestDocsTest extends InitRestDocsTest {

    @Test
    @DisplayName("사용자의 댓글 등록 요청이 정상적인 경우 댓글 생성 후 상태코드를 반환한다.")
    void post_register() {
        CommentRegisterCommand commentRegisterCommand = new CommentRegisterCommand("유저네임", "비밀번호123", "댓글 본문");
        given(this.spec)
            .filter(
                document("comment-register",
                    requestFields(
                        fieldWithPath("username").description("사용자 이름").type(JsonFieldType.STRING),
                        fieldWithPath("password").description("비밀번호").type(JsonFieldType.STRING),
                        fieldWithPath("content").description("댓글 본문").type(JsonFieldType.STRING)
                    ),
                    responseFields(
                        fieldWithPath("id").description("댓글 아이디").type(JsonFieldType.NUMBER),
                        fieldWithPath("username").description("사용자 이름").type(JsonFieldType.STRING),
                        fieldWithPath("content").description("요청한 댓글 본문").type(JsonFieldType.STRING)
                    )
                )
            )
            .log().all()
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .header("Content-type", MediaType.APPLICATION_JSON_VALUE)
            .pathParam("questionId", "1")
            .body(commentRegisterCommand)

        .when()
            .post("/api/golrabas/{questionId}/comments")

        .then()
            .log().all()
            .statusCode(HttpStatus.CREATED.value());
    }

}
