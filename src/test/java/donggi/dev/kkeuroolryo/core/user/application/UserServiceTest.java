package donggi.dev.kkeuroolryo.core.user.application;

import donggi.dev.kkeuroolryo.IntegrationTest;
import donggi.dev.kkeuroolryo.core.user.LoginResponseDto;
import donggi.dev.kkeuroolryo.core.user.domain.User;
import donggi.dev.kkeuroolryo.core.user.domain.UserRepository;
import donggi.dev.kkeuroolryo.web.user.dto.LoginRequestDto;
import org.assertj.core.api.SoftAssertions;
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
        user = userRepository.save(new User("loginId", "password"));
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
                LoginResponseDto loginResponseDto = userService.login(loginRequestDto);

                SoftAssertions.assertSoftly(softly -> {
                    softly.assertThat(loginResponseDto.loginId()).isEqualTo(loginId);
                    softly.assertThat(loginResponseDto.accessToken()).isNotEmpty();
                    softly.assertThat(loginResponseDto.refreshToken()).isNotEmpty();
                });

            }
        }
    }
}
