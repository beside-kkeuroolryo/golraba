package donggi.dev.kkeuroolryo.core.user.application.dto;

public record LoginResponseDto(
    String loginId,
    String accessToken,
    String refreshToken
) {

}
