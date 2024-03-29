package donggi.dev.kkeuroolryo.question.application.dto;

import donggi.dev.kkeuroolryo.question.domain.Category;
import donggi.dev.kkeuroolryo.question.domain.Question;
import donggi.dev.kkeuroolryo.question.domain.QuestionResult;
import lombok.Getter;

@Getter
public class QuestionDto {

    private Long id;
    private Category category;
    private String content;
    private boolean active;
    private String choiceA;
    private String choiceB;
    private int choiceAResult;
    private int choiceBResult;

    public static QuestionDto ofEntity(Question question, QuestionResult questionResult) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.id = question.getId();
        questionDto.category = question.getCategory();
        questionDto.content = question.getContent().getContent();
        questionDto.active = question.isActive();
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
        questionDto.category = question.getCategory();
        questionDto.content = question.getContent().getContent();
        questionDto.active = question.isActive();
        questionDto.choiceA = question.getChoiceA().getChoice();
        questionDto.choiceB = question.getChoiceB().getChoice();
        return questionDto;
    }

    private static int calculateChoiceCount(int totalChoices, int targetResult) {
        return (int) Math.round((double) targetResult / totalChoices * 100);
    }
}
