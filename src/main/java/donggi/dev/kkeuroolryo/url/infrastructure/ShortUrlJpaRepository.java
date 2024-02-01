package donggi.dev.kkeuroolryo.url.infrastructure;

import donggi.dev.kkeuroolryo.url.domain.Url;
import donggi.dev.kkeuroolryo.url.domain.exception.UrlNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortUrlJpaRepository extends JpaRepository<Url, Long> {

    default Url getById(Long id) {
        return findById(id).orElseThrow(UrlNotFoundException::new);
    }
}
