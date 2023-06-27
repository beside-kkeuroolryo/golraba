package donggi.dev.kkeuroolryo.core.question.domain;

import donggi.dev.kkeuroolryo.core.question.domain.exception.QuestionInvalidCategoryException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class QuestionCategory {

    @Column(nullable = false)
    private String category;

    public QuestionCategory(String category) {
        if (Objects.isNull(category)
            || category.isEmpty()
            || category.chars().allMatch(Character::isWhitespace)) {
            throw new QuestionInvalidCategoryException();
        }
        this.category = category;
    }
}
