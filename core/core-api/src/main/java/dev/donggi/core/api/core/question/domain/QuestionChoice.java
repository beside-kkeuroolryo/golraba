package dev.donggi.core.api.core.question.domain;

import dev.donggi.core.api.core.question.domain.exception.QuestionInvalidChoiceException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class QuestionChoice {

    @Column(nullable = false)
    private String choice;

    public QuestionChoice(String choice) {
        if (Objects.isNull(choice)
            || choice.isEmpty()
            || choice.chars().allMatch(Character::isWhitespace)) {
            throw new QuestionInvalidChoiceException();
        }

        this.choice = choice;
    }
}
