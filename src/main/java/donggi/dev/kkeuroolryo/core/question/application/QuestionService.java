package donggi.dev.kkeuroolryo.core.question.application;

import static donggi.dev.kkeuroolryo.core.question.domain.Category.COUPLE;
import static donggi.dev.kkeuroolryo.core.question.domain.Category.FRIEND;
import static donggi.dev.kkeuroolryo.core.question.domain.Category.RANDOM;
import static donggi.dev.kkeuroolryo.core.question.domain.Category.SELF;

import donggi.dev.kkeuroolryo.core.comment.domain.exception.NoOffsetPageInvalidException;
import donggi.dev.kkeuroolryo.core.question.application.dto.QuestionDto;
import donggi.dev.kkeuroolryo.core.question.application.dto.QuestionPaginationDto;
import donggi.dev.kkeuroolryo.core.question.application.dto.RandomQuestionsDto;
import donggi.dev.kkeuroolryo.core.question.domain.Category;
import donggi.dev.kkeuroolryo.core.question.domain.Question;
import donggi.dev.kkeuroolryo.core.question.domain.QuestionRepository;
import donggi.dev.kkeuroolryo.core.question.domain.QuestionResult;
import donggi.dev.kkeuroolryo.core.question.domain.QuestionResultRepository;
import donggi.dev.kkeuroolryo.core.question.domain.exception.QuestionInvalidChoiceException;
import donggi.dev.kkeuroolryo.core.question.domain.exception.QuestionNotFoundException;
import donggi.dev.kkeuroolryo.web.comment.dto.NoOffsetPageCommand;
import donggi.dev.kkeuroolryo.web.question.dto.QuestionRegisterDto;
import donggi.dev.kkeuroolryo.web.question.dto.QuestionResultCommand;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QuestionService implements QuestionFinder, QuestionEditor {

    private static final Long QUESTION_PAGE_LIMIT_SIZE = 20L;
    public static final int RANDOM_QUESTION_COUNT = 15;
    public static final String CHOICE_A = "a";
    public static final String CHOICE_B = "b";

    private final QuestionRepository questionRepository;
    private final QuestionResultRepository questionResultRepository;

    @Override
    @Transactional
    public QuestionDto save(QuestionRegisterDto questionRegisterDto) {
        Question question = questionRepository.save(questionRegisterDto.convertToEntity());

        questionResultRepository.save(new QuestionResult(question));

        return QuestionDto.ofEntity(question);
    }

    @Override
    @Transactional
    public void result(QuestionResultCommand resultCommand) {
        resultCommand.getResults()
            .forEach(choiceResult -> {
                QuestionResult questionResult = questionResultRepository.findByQuestionWithPessimisticLock(choiceResult.getQuestionId())
                    .orElseThrow(QuestionNotFoundException::new);

                updateChoice(choiceResult.getChoice(), questionResult);
                }
            );
    }

    @Override
    @Transactional
    public void changeActive(Long questionId, boolean active) {
        Question question = questionRepository.getById(questionId);

        question.changeActive(active);
    }

    @Override
    @Transactional
    public void modify(Long questionId, QuestionRegisterDto questionRegisterDto) {
        Question question = questionRepository.getById(questionId);

        question.modify(questionRegisterDto);
    }

    private void updateChoice(String choice, QuestionResult questionResult) {
        if (CHOICE_A.equals(choice)) {
            questionResult.incrementChoiceA();
        } else if (CHOICE_B.equals(choice)) {
            questionResult.incrementChoiceB();
        } else {
            throw new QuestionInvalidChoiceException();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public RandomQuestionsDto getRandomQuestionsByCategory(Category category) {
        List<Long> questionIds = retrieveQuestionIdsByCategory(category);

        List<Long> randomQuestionIds = getRandomQuestionIds(questionIds);

        return RandomQuestionsDto.ofEntity(category, randomQuestionIds);
    }

    private List<Long> retrieveQuestionIdsByCategory(Category category) {
        List<Category> categories;
        if (category.equals(RANDOM)) {
            categories = Arrays.asList(FRIEND, SELF, COUPLE);
        } else {
            categories = Collections.singletonList(category);
        }

        return getAllIdsByCategories(categories);
    }

    private List<Long> getAllIdsByCategories(List<Category> categories) {
        return questionRepository.findAllByIdInCategories(categories);
    }

    private List<Long> getRandomQuestionIds(List<Long> questionIds) {
        Collections.shuffle(questionIds);
        return questionIds.subList(0, Math.min(questionIds.size(), RANDOM_QUESTION_COUNT));
    }

    @Override
    @Transactional(readOnly = true)
    public QuestionDto getQuestion(Long questionId) {
        Question question = questionRepository.getById(questionId);

        return QuestionDto.ofEntity(question, question.getQuestionResult());
    }

    @Override
    @Transactional(readOnly = true)
    public QuestionPaginationDto findAllBy(NoOffsetPageCommand pageCommand) {
        checkNoOffsetPageSize(pageCommand.getSize());

        Long searchAfterId = pageCommand.getSearchAfterId() == 0
            ? questionRepository.getMaxId()
            : pageCommand.getSearchAfterId();

        Slice<Question> sliceQuestions = questionRepository.findAllBySearchAfterIdAndPageable(searchAfterId,
            Pageable.ofSize(Math.toIntExact(pageCommand.getSize())));

        return QuestionPaginationDto.ofEntity(sliceQuestions);
    }

    @Override
    public QuestionPaginationDto search(String keyword, NoOffsetPageCommand noOffsetPageCommand) {
        return null;
    }

    private void checkNoOffsetPageSize(Long size) {
        if (size > QUESTION_PAGE_LIMIT_SIZE) {
            throw new NoOffsetPageInvalidException();
        }
    }
}
