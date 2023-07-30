package dev.donggi.core.api.core.question;

import dev.donggi.core.api.core.comment.exception.NoOffsetPageInvalidException;
import dev.donggi.core.api.core.question.application.dto.QuestionDto;
import dev.donggi.core.api.core.question.dto.QuestionPaginationDto;
import dev.donggi.core.api.core.question.domain.Question;
import dev.donggi.core.api.core.question.domain.exception.QuestionNotFoundException;
import dev.donggi.core.api.web.comment.dto.NoOffsetPageCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminQuestionService implements AdminQuestionFinder {

    public static final Long QUESTION_PAGE_LIMIT_SIZE = 30L;

    private final AdminQuestionRepository questionRepository;

    @Override
    @Transactional(readOnly = true)
    public QuestionPaginationDto searchByContent(String content, NoOffsetPageCommand pageCommand) {
        checkNoOffsetPageSize(pageCommand.getSize());

        Long searchAfterId = pageCommand.getSearchAfterId() == 0
            ? questionRepository.findMaxId().orElse(0L)
            : pageCommand.getSearchAfterId();

        Slice<Question> sliceQuestions = questionRepository.findByContent(content, searchAfterId,
            Pageable.ofSize(Math.toIntExact(pageCommand.getSize())));
        return QuestionPaginationDto.ofEntity(sliceQuestions, content);
    }

    private void checkNoOffsetPageSize(Long size) {
        if (size > QUESTION_PAGE_LIMIT_SIZE) {
            throw new NoOffsetPageInvalidException();
        }
    }
}
