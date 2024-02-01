package donggi.dev.kkeuroolryo.user.application.dto;

public record LoginResponseDto(
    String loginId,
    String accessToken,
    String refreshToken
) {

}
