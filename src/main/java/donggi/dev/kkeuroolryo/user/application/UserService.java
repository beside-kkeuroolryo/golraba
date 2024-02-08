package donggi.dev.kkeuroolryo.user.application;

import donggi.dev.kkeuroolryo.user.application.dto.LoginTokens;
import donggi.dev.kkeuroolryo.user.domain.RefreshToken;
import donggi.dev.kkeuroolryo.user.domain.RefreshTokenRepository;
import donggi.dev.kkeuroolryo.user.domain.User;
import donggi.dev.kkeuroolryo.user.domain.UserRepository;
import donggi.dev.kkeuroolryo.user.domain.exception.DuplicatedLoginIdException;
import donggi.dev.kkeuroolryo.user.presentation.dto.LoginRequestDto;
import donggi.dev.kkeuroolryo.user.presentation.dto.SignupRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;

    @Transactional
    public LoginTokens login(final LoginRequestDto loginRequestDto) {
        final User user = userRepository.getByLoginId(loginRequestDto.loginId());
        user.checkPassword(loginRequestDto.password());

        final LoginTokens loginTokens = jwtProvider.generateLoginToken(user.getId().toString());
        final RefreshToken refreshToken = new RefreshToken(loginTokens.refreshToken(), user.getId());

        refreshTokenRepository.save(refreshToken);

        return loginTokens;
    }

    @Transactional
    public void signup(SignupRequestDto signupRequestDto) {
        if (userRepository.existsByLoginId(signupRequestDto.loginId())) {
            throw new DuplicatedLoginIdException();
        }

        userRepository.save(signupRequestDto.convertToEntity());
    }
}
