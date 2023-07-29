package dev.donggi.core.api.web.question.dto;

import dev.donggi.core.api.core.question.domain.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuestionRegisterCommand {

    private static final String USERMADE = "usermade";
    private String content;
    private String choiceA;
    private String choiceB;

    public QuestionRegisterCommand(String content, String choiceA, String choiceB) {
        this.content = content;
        this.choiceA = choiceA;
        this.choiceB = choiceB;
    }

    public Question convertToEntity() {
        return new Question(USERMADE, getContent(), getChoiceA(), getChoiceB());
    }
}
