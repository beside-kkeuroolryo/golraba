package donggi.dev.kkeuroolryo.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "refresh_token")
public class RefreshToken {

    @Id
    private String token;

    @Column(nullable = false)
    private Long userId;

    public RefreshToken(String token, Long userId) {
        this.token = token;
        this.userId = userId;
    }
}
