package donggi.dev.kkeuroolryo.core.question.application.dto;

import donggi.dev.kkeuroolryo.core.question.domain.Question;
import lombok.Getter;

@Getter
public class QuestionDto {

    private Long id;
    private String content;
    private String choiceA;
    private String choiceB;

    public static QuestionDto ofEntity(Question question) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.id = question.getId();
        questionDto.content = question.getContent().getContent();
        questionDto.choiceA = question.getChoiceA().getChoice();
        questionDto.choiceB = question.getChoiceB().getChoice();
        return questionDto;
    }
}
