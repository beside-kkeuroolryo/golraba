package donggi.dev.kkeuroolryo.core.question.application.dto;

import donggi.dev.kkeuroolryo.core.question.domain.Question;
import lombok.Getter;

@Getter
public class QuestionDto {

    private Long id;
    private String content;
    private String choiceA;
    private String choiceB;
    private int choiceAResult;
    private int choiceBResult;

    public static QuestionDto ofEntity(Question question) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.id = question.getId();
        questionDto.content = question.getContent().getContent();
        questionDto.choiceA = question.getChoiceA().getChoice();
        questionDto.choiceB = question.getChoiceB().getChoice();
        int totalChoices = question.getQuestionResult().getChoiceAResult() + question.getQuestionResult().getChoiceBResult();
        questionDto.choiceAResult = foo(totalChoices, question.getQuestionResult().getChoiceAResult());
        questionDto.choiceBResult = foo(totalChoices, question.getQuestionResult().getChoiceBResult());
        return questionDto;
    }

    private static int foo(int totalChoices, int targetResult) {
        return (int) Math.round((double) targetResult / totalChoices * 100);
    }
}
