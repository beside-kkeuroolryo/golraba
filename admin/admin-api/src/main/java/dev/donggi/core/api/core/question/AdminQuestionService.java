package dev.donggi.core.api.core.question;

import dev.donggi.core.api.core.comment.exception.NoOffsetPageInvalidException;
import dev.donggi.core.api.core.question.domain.Question;
import dev.donggi.core.api.core.question.domain.QuestionRepository;
import dev.donggi.core.api.core.question.domain.QuestionResult;
import dev.donggi.core.api.core.question.domain.QuestionResultRepository;
import dev.donggi.core.api.core.question.domain.exception.QuestionNotFoundException;
import dev.donggi.core.api.core.question.dto.AdminQuestionDto;
import dev.donggi.core.api.core.question.dto.QuestionPaginationDto;
import dev.donggi.core.api.web.comment.dto.NoOffsetPageCommand;
import dev.donggi.core.api.web.question.dto.AdminQuestionRegisterCommand;
import dev.donggi.core.api.web.question.dto.AdminQuestionUpdateCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminQuestionService implements AdminQuestionFinder, AdminQuestionEditor {

    public static final Long QUESTION_PAGE_LIMIT_SIZE = 30L;

    private final AdminQuestionRepository adminQuestionRepository;
    private final QuestionRepository questionRepository;
    private final QuestionResultRepository questionResultRepository;

    @Override
    @Transactional(readOnly = true)
    public QuestionPaginationDto searchByContent(String content, NoOffsetPageCommand pageCommand) {
        checkNoOffsetPageSize(pageCommand.getSize());

        Long searchAfterId = pageCommand.getSearchAfterId() == 0
            ? adminQuestionRepository.findMaxId().orElse(0L)
            : pageCommand.getSearchAfterId();

        Slice<Question> sliceQuestions = adminQuestionRepository.findByContent(content, searchAfterId,
            Pageable.ofSize(Math.toIntExact(pageCommand.getSize())));
        return QuestionPaginationDto.ofEntity(sliceQuestions, content);
    }

    private void checkNoOffsetPageSize(Long size) {
        if (size > QUESTION_PAGE_LIMIT_SIZE) {
            throw new NoOffsetPageInvalidException();
        }
    }

    @Override
    @Transactional
    public AdminQuestionDto save(AdminQuestionRegisterCommand adminQuestionRegisterCommand) {
        Question question = questionRepository.save(adminQuestionRegisterCommand.convertToEntity());

        questionResultRepository.save(new QuestionResult(question));

        return AdminQuestionDto.ofEntity(question);
    }

    @Override
    @Transactional
    public void update(Long questionId, AdminQuestionUpdateCommand updateCommand) {
        Question foundQuestion = questionRepository.findById(questionId)
            .orElseThrow(QuestionNotFoundException::new);

        foundQuestion.update(updateCommand.getContent(), updateCommand.getCategory(),
                             updateCommand.getChoiceA(), updateCommand.getChoiceB());
    }

    @Override
    @Transactional
    public void delete(Long questionId) {
        Question foundQuestion = questionRepository.findById(questionId)
            .orElseThrow(QuestionNotFoundException::new);

        adminQuestionRepository.delete(foundQuestion);
    }
}
