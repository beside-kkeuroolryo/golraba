package donggi.dev.kkeuroolryo.core.user.application;

import donggi.dev.kkeuroolryo.core.user.LoginResponseDto;
import donggi.dev.kkeuroolryo.core.user.domain.User;
import donggi.dev.kkeuroolryo.core.user.domain.UserRepository;
import donggi.dev.kkeuroolryo.web.user.dto.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public LoginResponseDto login(final LoginRequestDto loginRequestDto) {
        User user = userRepository.getByLoginId(loginRequestDto.loginId());

        return null;
    }
}
