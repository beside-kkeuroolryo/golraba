package donggi.dev.kkeuroolryo.web.question;

import static io.restassured.RestAssured.given;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document;

import donggi.dev.kkeuroolryo.InitRestDocsTest;
import donggi.dev.kkeuroolryo.RestAssuredAndRestDocsTest;
import donggi.dev.kkeuroolryo.core.question.domain.Question;
import donggi.dev.kkeuroolryo.core.question.domain.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

@DisplayName("API 문서화 : Question Read")
@RestAssuredAndRestDocsTest
public class QuestionReadRestControllerRestDocsTest extends InitRestDocsTest {

    @Autowired
    QuestionRepository questionRepository;

    @BeforeEach
    void setUp() {
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
        questionRepository.save(new Question("self", "질문 본문16", "선택A 16", "선택B 16"));
    }

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
