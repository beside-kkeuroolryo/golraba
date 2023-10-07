package donggi.dev.kkeuroolryo.core.url.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortUrlJpaRepository extends JpaRepository<Url, String> {

}
