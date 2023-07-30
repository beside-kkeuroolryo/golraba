package dev.donggi.core.api.core.question.dto;

import dev.donggi.core.api.core.question.domain.Question;
import lombok.Getter;

@Getter

public class AdminQuestionDto {

    private Long id;
    private String content;
    private String category;
    private String choiceA;
    private String choiceB;

    public static AdminQuestionDto ofEntity(Question question) {
        AdminQuestionDto adminQuestionDto = new AdminQuestionDto();
        adminQuestionDto.id = question.getId();
        adminQuestionDto.content = question.getContent().getContent();
        adminQuestionDto.category = question.getCategory();
        adminQuestionDto.choiceA = question.getChoiceA().getChoice();
        adminQuestionDto.choiceB = question.getChoiceB().getChoice();
        return adminQuestionDto;
    }
}
