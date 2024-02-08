package donggi.dev.kkeuroolryo.user.presentation.dto;

import donggi.dev.kkeuroolryo.user.domain.User;

public record SignupRequestDto(
    String loginId,
    String password
) {
    public User convertToEntity() {
        return new User(loginId, password);
    }
}
