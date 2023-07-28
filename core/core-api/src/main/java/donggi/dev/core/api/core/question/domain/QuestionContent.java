package donggi.dev.core.api.core.question.domain;

import donggi.dev.core.api.core.question.domain.exception.QuestionInvalidContentException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class QuestionContent {

    private static final int LIMIT_LENGTH = 100;

    @Column(length = LIMIT_LENGTH, nullable = false)
    private String content;

    public QuestionContent(String content) {
        if (Objects.isNull(content)
            || content.isEmpty()
            || content.chars().allMatch(Character::isWhitespace)
            || content.length() > LIMIT_LENGTH) {
            throw new QuestionInvalidContentException();
        }

        this.content = content;
    }
}
