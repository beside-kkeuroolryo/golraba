package donggi.dev.kkeuroolryo.web.question.dto;

import donggi.dev.kkeuroolryo.core.question.domain.Category;
import donggi.dev.kkeuroolryo.core.question.domain.Question;

public record QuestionRegisterDto(
    String content,
    String choiceA,
    String choiceB,
    Category category
) {
    public Question convertToEntity() {
        return new Question(content, choiceA, choiceB, category);
    }
}
