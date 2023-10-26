package donggi.dev.kkeuroolryo.web.user.dto;

import donggi.dev.kkeuroolryo.core.user.domain.User;

public record LoginRequestDto(
    String loginId,
    String password
) {
    public User convertToEntity() {
        return new User(loginId, password);
    }
}
