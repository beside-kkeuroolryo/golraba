package donggi.dev.kkeuroolryo.core.user.infrastructure;

import donggi.dev.kkeuroolryo.core.user.domain.RefreshToken;
import donggi.dev.kkeuroolryo.core.user.domain.RefreshTokenRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenJpaRepository extends RefreshTokenRepository, JpaRepository<RefreshToken, Long> {

    RefreshToken save(final RefreshToken refreshToken);
}
