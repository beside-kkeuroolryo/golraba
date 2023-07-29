package donggi.dev.kkeuroolryo.web.comment;

import static io.restassured.RestAssured.given;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document;

import donggi.dev.kkeuroolryo.InitRestDocsTest;
import donggi.dev.kkeuroolryo.RestAssuredAndRestDocsTest;
import dev.donggi.core.api.core.comment.domain.Comment;
import dev.donggi.core.api.core.comment.domain.CommentRepository;
import dev.donggi.core.api.core.question.domain.Question;
import dev.donggi.core.api.core.question.domain.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

@DisplayName("API 문서화 : 댓글 조회")
@RestAssuredAndRestDocsTest
public class CommentReadRestControllerRestDocsTest extends InitRestDocsTest {

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
        comment = commentRepository.save(new Comment(question.getId(), "사용자이름1", "비밀번호123", "댓글본문1"));
        comment = commentRepository.save(new Comment(question.getId(), "사용자이름2", "비밀번호123", "댓글본문2"));
        comment = commentRepository.save(new Comment(question.getId(), "사용자이름3", "비밀번호123", "댓글본문3"));
        comment = commentRepository.save(new Comment(question.getId(), "사용자이름4", "비밀번호123", "댓글본문4"));
        comment = commentRepository.save(new Comment(question.getId(), "사용자이름5", "비밀번호123", "댓글본문5"));
    }

    @Test
    @DisplayName("가장 최신 글부터 5개의 글 조회 요청이 주어진다면 댓글을 반환하고 정상 상태코드를 반환한다.")
    void comments_read() {
        given(this.spec)
            .filter(
                document("comments-read",
                    pathParameters(parameterWithName("questionId").description("질문 id")),
                    queryParameters(
                        parameterWithName("searchAfterId").description("검색 시작할 댓글 id"),
                        parameterWithName("size").description("조회할 데이터 개수")
                    ),
                    responseFields(
                        fieldWithPath("code").description("응답 코드").type(JsonFieldType.STRING),
                        fieldWithPath("message").description("응답 메세지").type(JsonFieldType.STRING),
                        fieldWithPath("data.questionId").description("질문 id").type(JsonFieldType.NUMBER),
                        fieldWithPath("data.comments[].id").description("댓글 id").type(JsonFieldType.NUMBER),
                        fieldWithPath("data.comments[].username").description("사용자 이름").type(JsonFieldType.STRING),
                        fieldWithPath("data.comments[].content").description("댓글 본문").type(JsonFieldType.STRING),

                        fieldWithPath("data.page.size").description("조회된 데이터 개수").type(JsonFieldType.NUMBER),
                        fieldWithPath("data.page.nextId").description("다음 데이터 id").type(JsonFieldType.NUMBER),
                        fieldWithPath("data.page.last").description("마지막 여부").type(JsonFieldType.BOOLEAN)
                    )
                )
            )
            .log().all()
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .header("Content-type", MediaType.APPLICATION_JSON_VALUE)
            .queryParam("searchAfterId", comment.getId())
            .queryParam("size", 5)

        .when()
            .get("/api/golrabas/{questionId}/comments", question.getId())

        .then()
            .log().all()
            .statusCode(HttpStatus.OK.value());
    }
}
