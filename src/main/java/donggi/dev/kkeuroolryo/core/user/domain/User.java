package donggi.dev.kkeuroolryo.core.user.domain;

import donggi.dev.kkeuroolryo.core.user.domain.exception.InvalidPasswordException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 20)
    private String loginId;
    @Column(length = 20, nullable = false)
    private String password;
    private boolean active;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public User(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
        this.active = false;
    }

    public void checkPassword(final String password) {
        if (!this.password.equals(password)) {
            throw new InvalidPasswordException();
        }
    }
}
