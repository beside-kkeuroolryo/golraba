package donggi.dev.kkeuroolryo.core.user;

public record LoginResponseDto(
    String loginId,
    String accessToken,
    String refreshToken
) {

}
