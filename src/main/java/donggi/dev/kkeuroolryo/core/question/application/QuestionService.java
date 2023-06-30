package donggi.dev.kkeuroolryo.core.question.application;

import donggi.dev.kkeuroolryo.core.question.application.dto.QuestionDto;
import donggi.dev.kkeuroolryo.core.question.application.dto.RandomQuestionsDto;
import donggi.dev.kkeuroolryo.core.question.domain.Question;
import donggi.dev.kkeuroolryo.core.question.domain.QuestionRepository;
import donggi.dev.kkeuroolryo.core.question.domain.exception.QuestionNotFoundException;
import donggi.dev.kkeuroolryo.web.question.dto.QuestionRegisterCommand;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QuestionService implements QuestionFinder, QuestionEditor {

    public static final int RANDOM_QUESTION_COUNT = 15;

    private final QuestionRepository questionRepository;

    @Override
    @Transactional
    public QuestionDto save(QuestionRegisterCommand questionRegisterCommand) {
        Question question = questionRepository.save(questionRegisterCommand.convertToEntity());
        return QuestionDto.ofEntity(question);
    }

    @Override
    @Transactional(readOnly = true)
    public RandomQuestionsDto getRandomQuestionsByCategory(String category) {
        List<Long> questionIds = questionRepository.findAllIdsByCategory(category);

        List<Long> randomQuestionIds = getRandomQuestionIds(questionIds);

        return RandomQuestionsDto.ofEntity(category, randomQuestionIds);
    }

    private List<Long> getRandomQuestionIds(List<Long> questionIds) {
        Collections.shuffle(questionIds);
        return questionIds.subList(0, Math.min(questionIds.size(), RANDOM_QUESTION_COUNT));
    }

    @Override
    @Transactional(readOnly = true)
    public QuestionDto getQuestion(Long questionId) {
        Question question = questionRepository.findById(questionId)
            .orElseThrow(QuestionNotFoundException::new);

        return QuestionDto.ofEntity(question);
    }
}
