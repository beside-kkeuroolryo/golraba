package donggi.dev.kkeuroolryo.core.question.application;

import donggi.dev.kkeuroolryo.core.question.domain.Question;
import donggi.dev.kkeuroolryo.core.question.domain.QuestionRepository;
import donggi.dev.kkeuroolryo.core.question.application.dto.RandomQuestionsDto;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QuestionService implements QuestionFinder {

    public static final int RANDOM_QUESTION_COUNT = 15;
    private final QuestionRepository questionRepository;

    @Override
    @Transactional(readOnly = true)
    public RandomQuestionsDto getRandomQuestionsByCategory(String category) {
        List<Long> questionIds = questionRepository.findAllIdsByCategory(category);

        List<Long> randomQuestionIds = getRandomQuestionIds(questionIds);

        List<Question> randomQuestions = questionRepository.findByIdIn(randomQuestionIds);

        return RandomQuestionsDto.ofEntity(category, randomQuestions);
    }

    private List<Long> getRandomQuestionIds(List<Long> questionIds) {
        Collections.shuffle(questionIds);
        return questionIds.subList(0, Math.min(questionIds.size(), RANDOM_QUESTION_COUNT));
    }
}
