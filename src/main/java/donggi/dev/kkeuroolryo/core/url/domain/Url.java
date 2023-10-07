package donggi.dev.kkeuroolryo.core.url.domain;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "url")
public class Url {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String originalUrl;
}
