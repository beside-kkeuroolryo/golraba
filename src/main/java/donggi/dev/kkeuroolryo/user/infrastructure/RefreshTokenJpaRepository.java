package donggi.dev.kkeuroolryo.user.infrastructure;

import donggi.dev.kkeuroolryo.user.domain.RefreshToken;
import donggi.dev.kkeuroolryo.user.domain.RefreshTokenRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenJpaRepository extends RefreshTokenRepository, JpaRepository<RefreshToken, Long> {

    RefreshToken save(final RefreshToken refreshToken);
}
