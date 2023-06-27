package donggi.dev.kkeuroolryo.core.comment.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CommentUsername {

    @Column(nullable = false)
    private String username;

    public CommentUsername(String username) {
        this.username = username;
    }
}
