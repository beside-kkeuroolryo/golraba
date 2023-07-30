package dev.donggi.core.api.web.question.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdminQuestionUpdateCommand {

    private String content;
    private String category;
    private String choiceA;
    private String choiceB;

    public AdminQuestionUpdateCommand(String content, String category, String choiceA,
                                      String choiceB) {
        this.content = content;
        this.category = category;
        this.choiceA = choiceA;
        this.choiceB = choiceB;
    }
}
