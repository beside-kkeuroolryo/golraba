package donggi.dev.kkeuroolryo.web.url;

import static io.restassured.RestAssured.given;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document;

import donggi.dev.kkeuroolryo.InitRestDocsTest;
import donggi.dev.kkeuroolryo.RestAssuredAndRestDocsTest;
import donggi.dev.kkeuroolryo.core.url.application.ShortUrlService;
import donggi.dev.kkeuroolryo.core.url.domain.ShortUrlJpaRepository;
import donggi.dev.kkeuroolryo.core.url.domain.Url;
import donggi.dev.kkeuroolryo.core.url.dto.ShortUrlResponseDto;
import donggi.dev.kkeuroolryo.web.url.dto.ShortUrlRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

@DisplayName("API 문서화 : short url 생성")
@RestAssuredAndRestDocsTest
class ShortUrlRestControllerTest extends InitRestDocsTest {

    @Autowired
    ShortUrlJpaRepository shortUrlRepository;

    @Autowired
    ShortUrlService shortUrlService;

    Url url;
    @BeforeEach
    void setUp() {
        shortUrlRepository.deleteAllInBatch();

        ShortUrlRequestDto shortUrlRequestDto = new ShortUrlRequestDto("originalData");
        url = shortUrlRepository.save(shortUrlRequestDto.convertToEntity());
    }

    @Test
    @DisplayName("url 줄이기 요청이 정상적이면 short url을 생성하고 상태 코드를 반환한다.")
    void short_url_register() {
        ShortUrlRequestDto shortUrlRequestDto = new ShortUrlRequestDto("originalData");
        given(this.spec)
            .filter(
                document("short-url-encode",
                    requestFields(
                        fieldWithPath("originalData").description("원본 url").type(JsonFieldType.STRING)
                    ),
                    responseFields(
                        fieldWithPath("code").description("응답 코드").type(JsonFieldType.STRING),
                        fieldWithPath("message").description("응답 메세지").type(JsonFieldType.STRING),
                        fieldWithPath("data.shortUrl").description("생성된 short url").type(JsonFieldType.STRING)
                    )
                )
            )
            .log().all()
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .header("Content-type", MediaType.APPLICATION_JSON_VALUE)
            .body(shortUrlRequestDto)

        .when()
            .post("/api/golrabas/shortUrl")

        .then()
            .log().all()
            .statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("원본 url 조회 요청이 요청이 정상적이면 원본 url을 반환하고 상태 코드를 반환한다.")
    void short_url_decode() {
        ShortUrlResponseDto shortUrlResponseDto = shortUrlService.encodeShortUrl(
            new ShortUrlRequestDto("originalData"));
        given(this.spec)
            .filter(
                document("short-url-decode",
                    pathParameters(
                        parameterWithName(("shortUrl")).description("요청 url")
                    ),
                    responseFields(
                        fieldWithPath("code").description("응답 코드").type(JsonFieldType.STRING),
                        fieldWithPath("message").description("응답 메세지").type(JsonFieldType.STRING),
                        fieldWithPath("data.originalData").description("원본 url").type(JsonFieldType.STRING)
                    )
                )
            )
            .log().all()
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .header("Content-type", MediaType.APPLICATION_JSON_VALUE)

        .when()
            .get("/api/golrabas/shortUrl/{shortUrl}", shortUrlResponseDto.shortUrl())

        .then()
            .log().all()
            .statusCode(HttpStatus.OK.value());
    }
}
