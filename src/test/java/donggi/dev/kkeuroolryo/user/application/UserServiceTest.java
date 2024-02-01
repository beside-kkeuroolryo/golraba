package donggi.dev.kkeuroolryo.user.application;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import donggi.dev.kkeuroolryo.support.IntegrationTest;
import donggi.dev.kkeuroolryo.user.application.dto.LoginTokens;
import donggi.dev.kkeuroolryo.user.domain.User;
import donggi.dev.kkeuroolryo.user.domain.UserRepository;
import donggi.dev.kkeuroolryo.user.presentation.dto.LoginRequestDto;
import donggi.dev.kkeuroolryo.user.presentation.dto.SignupRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("유저 로그인 IntegrationTest")
@IntegrationTest
class UserServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    User user;

    @BeforeEach
    void setUp() {
        userRepository.deleteAllInBatch();

        SignupRequestDto signupRequestDto = new SignupRequestDto("loginId", "password");

        user = userRepository.save(signupRequestDto.convertToEntity());
    }

    @Nested
    @DisplayName("유저 로그인 요청이")
    class Describe_login {

        @Nested
        @DisplayName("정상적인 요청이면")
        class Context_with_valid_login_command {

            @ParameterizedTest
            @CsvSource({"loginId,password"})
            @DisplayName("액세스 토큰과 리프레쉬 토큰을 담은 DTO 객체를 반환한다.")
            void return_login_response_dto(String loginId, String password) {
                LoginRequestDto loginRequestDto = new LoginRequestDto(loginId, password);
                LoginTokens loginTokens = userService.login(loginRequestDto);

                assertSoftly(softly -> {
                    softly.assertThat(loginTokens.accessToken()).isNotNull();
                    softly.assertThat(loginTokens.refreshToken()).isNotNull();
                });
            }
        }
    }
}
