package dev.donggi.core.api.core.comment.domain;

import dev.donggi.core.api.core.comment.exception.CommentInvalidPasswordException;
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

    private static final int MINIMUM_LENGTH = 4;
    private static final int LIMIT_LENGTH = 15;

    @Column(nullable = false)
    private String password;

    public CommentPassword(String password) {
        if (Objects.isNull(password)
            || password.isEmpty()
            || password.chars().allMatch(Character::isWhitespace)
            || password.length() < MINIMUM_LENGTH
            || password.length() > LIMIT_LENGTH) {
            throw new CommentInvalidPasswordException();
        }

        this.password = password;
    }
}
