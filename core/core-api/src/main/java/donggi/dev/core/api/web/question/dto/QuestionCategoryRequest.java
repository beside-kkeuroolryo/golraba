package donggi.dev.core.api.web.question.dto;

import donggi.dev.core.api.core.question.domain.Category;
import donggi.dev.core.api.core.question.domain.exception.QuestionInvalidCategoryException;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuestionCategoryRequest {

    private String category;

    public QuestionCategoryRequest(String category) {
        if (Objects.isNull(category)
            || category.isEmpty()
            || category.chars().allMatch(Character::isWhitespace)
            || !isValidCategory(category)) {
            throw new QuestionInvalidCategoryException();
        }

        this.category = category;
    }

    private boolean isValidCategory(String category) {
        try {
            Category.valueOf(category.toUpperCase());
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}
