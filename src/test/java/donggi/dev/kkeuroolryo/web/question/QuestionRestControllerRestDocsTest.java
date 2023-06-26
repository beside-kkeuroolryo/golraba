package donggi.dev.kkeuroolryo.web.question;

import static io.restassured.RestAssured.given;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.documentationConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import donggi.dev.kkeuroolryo.web.question.dto.QuestionRegisterRequest;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.internal.mapping.Jackson2Mapper;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.ActiveProfiles;

@DisplayName("API 문서화 : 유저 골라바 질문 등록 요청")
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(RestDocumentationExtension.class)
class QuestionRestControllerRestDocsTest {

    @Autowired
    private ObjectMapper objectMapper;

    @LocalServerPort
    private int port;

    private RequestSpecification spec;

    {
        setUpRestAssured();
    }

    @BeforeEach
    void setUpRestDocs(RestDocumentationContextProvider restDocumentation) {
        RestAssured.port = port;

        this.spec = new RequestSpecBuilder()
            .setPort(port)
            .addFilter(documentationConfiguration(restDocumentation)
                .operationPreprocessors()
                .withRequestDefaults(prettyPrint())
                .withResponseDefaults(prettyPrint()))
            .build();
    }

    private void setUpRestAssured() {
        RestAssured.config = RestAssuredConfig.config()
            .objectMapperConfig(
                new ObjectMapperConfig(
                    new Jackson2Mapper((type, charset) -> objectMapper)
                )
            );
    }

    @Test
    @DisplayName("골라바 질문 등록 요청이 정상적인 경우 질문 생성 후 상태코드를 반환한다.")
    void post_register() {
        QuestionRegisterRequest request = new QuestionRegisterRequest("질문 본문", "선택 A", "선택 B");
        given(this.spec)
            .filter(
                document("question-register",
                    requestFields(
                        fieldWithPath("content").description("골라바 질문 본문 본문").type(JsonFieldType.STRING),
                        fieldWithPath("choiceA").description("선택지 A").type(JsonFieldType.STRING),
                        fieldWithPath("choiceB").description("선택지 B").type(JsonFieldType.STRING)
                    ),
                    responseFields(
                        fieldWithPath("id").description("골라바 질문 아이디").type(JsonFieldType.NUMBER),
                        fieldWithPath("content").description("골라바 질문 본문 본문").type(JsonFieldType.STRING),
                        fieldWithPath("choiceA").description("선택지 A").type(JsonFieldType.STRING),
                        fieldWithPath("choiceB").description("선택지 B").type(JsonFieldType.STRING)
                    )
                )
            )
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .header("Content-type", MediaType.APPLICATION_JSON_VALUE)
            .body(request)

        .when()
            .post("/api/golrabas/question")

        .then()
            .statusCode(HttpStatus.CREATED.value());
    }
}
