package donggi.dev.kkeuroolryo.core.user;

public record LoginResponseDto(
    String accessToken,
    String refreshToken
) {

}
