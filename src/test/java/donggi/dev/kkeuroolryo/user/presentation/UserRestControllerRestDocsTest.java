package donggi.dev.kkeuroolryo.user.presentation;

import static io.restassured.RestAssured.given;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.restassured.RestAssuredRestDocumentation.document;

import donggi.dev.kkeuroolryo.support.InitRestDocsTest;
import donggi.dev.kkeuroolryo.support.RestAssuredAndRestDocsTest;
import donggi.dev.kkeuroolryo.user.domain.User;
import donggi.dev.kkeuroolryo.user.domain.UserRepository;
import donggi.dev.kkeuroolryo.user.presentation.dto.LoginRequestDto;
import donggi.dev.kkeuroolryo.user.presentation.dto.SignupRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

@DisplayName("API 문서화 : 유저 로그인, 회원가입")
@RestAssuredAndRestDocsTest
class UserRestControllerRestDocsTest extends InitRestDocsTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        userRepository.deleteAllInBatch();

        SignupRequestDto signupRequestDto = new SignupRequestDto("loginId", "password");
        user = userRepository.save(signupRequestDto.convertToEntity());
    }

    @Test
    @DisplayName("유저의 로그인 요청이 정상적인 경우 액세스 토큰과 리프레쉬 토큰을 발급한 후 상태 코드를 반환한다.")
    void user_login() {
        LoginRequestDto loginRequestDto = new LoginRequestDto("loginId", "password");
        given(this.spec)
            .filter(
                document("user-login",
                    requestFields(
                        fieldWithPath("loginId").description("로그인 아이디").type(JsonFieldType.STRING),
                        fieldWithPath("password").description("로그인 비밀번호").type(JsonFieldType.STRING)
                    ),
                    responseFields(
                        fieldWithPath("code").description("응답 코드").type(JsonFieldType.STRING),
                        fieldWithPath("message").description("응답 메세지").type(JsonFieldType.STRING),
                        fieldWithPath("data.accessToken").description("로그인 액세스 토큰").type(JsonFieldType.STRING),
                        fieldWithPath("data.refreshToken").description("로그인 리프레쉬 토큰").type(JsonFieldType.STRING)
                    )
                )
            )
            .log().all()
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .header("Content-type", MediaType.APPLICATION_JSON_VALUE)
            .body(loginRequestDto)

        .when()
            .post("/api/user/login")

        .then()
            .log().all()
            .statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("회원가입 요청이 정상적인 경우 입력한 유저 정보를 저장하고 상태 코드를 반환한다.")
    void user_signup() {
        SignupRequestDto signupRequestDto = new SignupRequestDto("loginId2", "password");
        given(this.spec)
            .filter(
                document("user-signup",
                    requestFields(
                        fieldWithPath("loginId").description("로그인 아이디").type(JsonFieldType.STRING),
                        fieldWithPath("password").description("로그인 비밀번호").type(JsonFieldType.STRING)
                    )
                )
            )
            .log().all()
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .header("Content-type", MediaType.APPLICATION_JSON_VALUE)
            .body(signupRequestDto)

        .when()
            .post("/api/user/signup")

        .then()
            .log().all()
            .statusCode(HttpStatus.OK.value());
    }
}
