package donggi.dev.kkeuroolryo.core.user.domain;

public interface RefreshTokenRepository {

    /**
     * 저장소에 리프레시 토큰을 저장합니다.
     * @param refreshToken
     * @return 저장한 refreshToken
     */
    RefreshToken save(final RefreshToken refreshToken);
}
