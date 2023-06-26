package donggi.dev.kkeuroolryo.core.question.application.dto;

import donggi.dev.kkeuroolryo.core.question.domain.Question;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class RandomQuestionsDto {

    private String category;
    private List<QuestionDto> questions = new ArrayList<>();

    public static RandomQuestionsDto ofEntity(String category, List<Question> questions) {
        RandomQuestionsDto randomQuestionsDto = new RandomQuestionsDto();

        randomQuestionsDto.category = (category);
        randomQuestionsDto.questions = questions.stream()
            .map(question -> QuestionDto.ofEntity(question))
            .collect(Collectors.toList());

        return randomQuestionsDto;
    }
}
