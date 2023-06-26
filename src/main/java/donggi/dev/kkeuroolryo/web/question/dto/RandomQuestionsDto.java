package donggi.dev.kkeuroolryo.web.question.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class RandomQuestionsDto {

    private String category;
    private List<QuestionDto> questions = new ArrayList<>();

    public RandomQuestionsDto(String category, List<QuestionDto> questions) {
        this.category = category;
        this.questions = questions;
    }
}
