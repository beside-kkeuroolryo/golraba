package donggi.dev.kkeuroolryo.core.question.application.dto;

import donggi.dev.kkeuroolryo.core.question.domain.Question;
import donggi.dev.kkeuroolryo.core.question.domain.QuestionResult;
import lombok.Getter;

@Getter
public class QuestionDto {

    private Long id;
    private String content;
    private String choiceA;
    private String choiceB;
    private int choiceAResult;
    private int choiceBResult;

    public static QuestionDto ofEntity(Question question, QuestionResult questionResult) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.id = question.getId();
        questionDto.content = question.getContent().getContent();
        questionDto.choiceA = question.getChoiceA().getChoice();
        questionDto.choiceB = question.getChoiceB().getChoice();
        int totalChoices = questionResult.getChoiceAResult() + questionResult.getChoiceBResult();
        questionDto.choiceAResult = calculateChoiceCount(totalChoices, questionResult.getChoiceAResult());
        questionDto.choiceBResult = calculateChoiceCount(totalChoices, questionResult.getChoiceBResult());
        return questionDto;
    }

    public static QuestionDto ofEntity(Question question) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.id = question.getId();
        questionDto.content = question.getContent().getContent();
        questionDto.choiceA = question.getChoiceA().getChoice();
        questionDto.choiceB = question.getChoiceB().getChoice();
        return questionDto;
    }

    private static int calculateChoiceCount(int totalChoices, int targetResult) {
        return (int) Math.round((double) targetResult / totalChoices * 100);
    }
}
