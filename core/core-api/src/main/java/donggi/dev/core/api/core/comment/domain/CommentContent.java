package donggi.dev.core.api.core.comment.domain;

import donggi.dev.core.api.core.comment.exception.CommentInvalidContentException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CommentContent {

    public static final int LIMIT_LENGTH = 100;

    @Column(nullable = false)
    private String content;

    public CommentContent(String content) {
        if (Objects.isNull(content)
            || content.isEmpty()
            || content.chars().allMatch(Character::isWhitespace)
            || content.length() > LIMIT_LENGTH) {
            throw new CommentInvalidContentException();
        }

        this.content = content;
    }

}
