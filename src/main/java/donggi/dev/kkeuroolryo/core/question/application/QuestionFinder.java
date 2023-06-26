package donggi.dev.kkeuroolryo.core.question.application;

import donggi.dev.kkeuroolryo.core.question.application.dto.RandomQuestionsDto;

public interface QuestionFinder {

    RandomQuestionsDto getRandomQuestionsByCategory(String category);
}
