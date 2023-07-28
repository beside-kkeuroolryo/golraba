package donggi.dev.core.api.core.question.application.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class RandomQuestionsDto {

    private String category;
    private List<Long> questionIds = new ArrayList<>();

    public static RandomQuestionsDto ofEntity(String category, List<Long> questionIds) {
        RandomQuestionsDto randomQuestionsDto = new RandomQuestionsDto();
        randomQuestionsDto.category = category;
        randomQuestionsDto.questionIds = questionIds;
        return randomQuestionsDto;
    }
}
