package donggi.dev.kkeuroolryo.web.comment;

import static io.restassured.RestAssured.given;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document;

import donggi.dev.kkeuroolryo.InitRestDocsTest;
import donggi.dev.kkeuroolryo.RestAssuredAndRestDocsTest;
import donggi.dev.kkeuroolryo.core.comment.domain.Comment;
import donggi.dev.kkeuroolryo.core.comment.domain.CommentRepository;
import donggi.dev.kkeuroolryo.core.question.domain.Question;
import donggi.dev.kkeuroolryo.core.question.domain.QuestionRepository;
import donggi.dev.kkeuroolryo.web.comment.dto.CommentDeleteCommand;
import donggi.dev.kkeuroolryo.web.comment.dto.CommentRegisterCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

@DisplayName("API 문서화 : 댓글 등록, 삭제 요청")
@RestAssuredAndRestDocsTest
public class CommentRestControllerRestDocsTest extends InitRestDocsTest {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    QuestionRepository questionRepository;

    Question question;
    Comment comment;

    @BeforeEach
    void setUp() {
        questionRepository.deleteAll();
        commentRepository.deleteAll();

        question = questionRepository.save(new Question("카테고리", "질문본문", "선택지A", "선택지B"));
        comment = commentRepository.save(new Comment(question.getId(), "사용자이름", "비밀번호123", "댓글본문"));
    }

    @Test
    @DisplayName("사용자의 댓글 등록 요청이 정상적인 경우 댓글 생성 후 상태코드를 반환한다.")
    void comment_register() {
        CommentRegisterCommand commentRegisterCommand = new CommentRegisterCommand("유저네임", "비밀번호123", "댓글 본문");
        given(this.spec)
            .filter(
                document("comment-register",
                    pathParameters(parameterWithName("questionId").description("댓글 id")),
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
            .body(commentRegisterCommand)

        .when()
            .post("/api/golrabas/{questionId}/comments", question.getId())

        .then()
            .log().all()
            .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("사용자의 댓글 삭제 요청이 정상적인 경우 댓글을 삭제 후 상태코드를 반환한다.")
    void comment_delete() {
        CommentDeleteCommand commentDeleteCommand = new CommentDeleteCommand("비밀번호123");
        given(this.spec)
            .filter(
                document("comment-delete",
                    pathParameters(
                        parameterWithName(("questionId")).description("질문 id"),
                        parameterWithName(("commentId")).description("댓글 id")
                    ),
                    requestFields(fieldWithPath("password").description("비밀번호").type(JsonFieldType.STRING))
                )
            )
            .log().all()
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .header("Content-type", MediaType.APPLICATION_JSON_VALUE)
            .body(commentDeleteCommand)

        .when()
            .delete("/api/golrabas/{questionId}/comments/{commentId}", question.getId(), comment.getId())

        .then()
            .log().all()
            .statusCode(HttpStatus.NO_CONTENT.value());
    }
}
