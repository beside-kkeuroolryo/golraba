package donggi.dev.kkeuroolryo.core.url.domain;

import donggi.dev.kkeuroolryo.core.url.domain.exception.UrlNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortUrlJpaRepository extends JpaRepository<Url, Long> {

    default Url getById(Long id) {
        return findById(id).orElseThrow(UrlNotFoundException::new);
    }
}
