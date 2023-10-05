package donggi.dev.kkeuroolryo.core.question.application.dto;

import donggi.dev.kkeuroolryo.core.question.domain.Category;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class RandomQuestionsDto {

    private Category category;
    private List<Long> questionIds = new ArrayList<>();

    public static RandomQuestionsDto ofEntity(Category category, List<Long> questionIds) {
        RandomQuestionsDto randomQuestionsDto = new RandomQuestionsDto();
        randomQuestionsDto.category = category;
        randomQuestionsDto.questionIds = questionIds;
        return randomQuestionsDto;
    }
}
