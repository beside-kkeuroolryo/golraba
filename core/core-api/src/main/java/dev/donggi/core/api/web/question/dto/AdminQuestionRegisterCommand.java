package dev.donggi.core.api.web.question.dto;

import dev.donggi.core.api.core.question.domain.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdminQuestionRegisterCommand {

    private String content;
    private String category;
    private String choiceA;
    private String choiceB;

    public AdminQuestionRegisterCommand(String content, String category, String choiceA,
                                        String choiceB) {
        this.content = content;
        this.category = category;
        this.choiceA = choiceA;
        this.choiceB = choiceB;
    }

    public Question convertToEntity() {
        return new Question(getCategory(), getContent(), getChoiceA(), getChoiceB());
    }
}
