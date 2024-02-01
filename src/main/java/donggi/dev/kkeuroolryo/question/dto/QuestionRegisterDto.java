package donggi.dev.kkeuroolryo.question.dto;

import donggi.dev.kkeuroolryo.question.domain.Category;
import donggi.dev.kkeuroolryo.question.domain.Question;

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
