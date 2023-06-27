package donggi.dev.kkeuroolryo.core.comment.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CommentPassword {

    @Column(nullable = false)
    private String password;

    public CommentPassword(String password) {
        this.password = password;
    }
}
