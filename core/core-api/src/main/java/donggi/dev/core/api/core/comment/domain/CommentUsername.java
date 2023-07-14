package donggi.dev.core.api.core.comment.domain;

import donggi.dev.core.api.core.comment.exception.CommentInvalidUsernameException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CommentUsername {

    private static final int LIMIT_LENGTH = 15;

    @Column(nullable = false)
    private String username;


    public CommentUsername(String username) {
        if (Objects.isNull(username)
            || username.isEmpty()
            || username.chars().allMatch(Character::isWhitespace)
            || username.length() > LIMIT_LENGTH) {
            throw new CommentInvalidUsernameException();
        }

        this.username = username;
    }
}
