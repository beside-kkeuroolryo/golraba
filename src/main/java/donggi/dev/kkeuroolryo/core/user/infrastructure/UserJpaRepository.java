package donggi.dev.kkeuroolryo.core.user.infrastructure;

import donggi.dev.kkeuroolryo.core.user.domain.User;
import donggi.dev.kkeuroolryo.core.user.domain.UserRepository;
import donggi.dev.kkeuroolryo.core.user.domain.exception.UserNotFoundException;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends UserRepository, JpaRepository<User, Long> {

    @Override
    default User getByLoginId(final String loginId) {
        return findByLoginId(loginId).orElseThrow(UserNotFoundException::new);
    }

    Optional<User> findByLoginId(final String loginId);

    User save(final User user);

    void deleteAllInBatch();

    boolean existsByLoginId(final String loginId);
}
