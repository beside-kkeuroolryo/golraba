package donggi.dev.kkeuroolryo.core.comment.domain;

import donggi.dev.kkeuroolryo.core.comment.domain.exception.CommentInvalidPasswordException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CommentPassword {

    private static final int MINIMUM_LENGTH = 6;

    @Column(nullable = false)
    private String password;

    public CommentPassword(String password) {
        if (Objects.isNull(password)
            || password.isEmpty()
            || password.chars().allMatch(Character::isWhitespace)
            || password.length() < MINIMUM_LENGTH) {
            throw new CommentInvalidPasswordException();
        }

        this.password = password;
    }
}
