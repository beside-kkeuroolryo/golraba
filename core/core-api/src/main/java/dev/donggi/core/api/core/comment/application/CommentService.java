package dev.donggi.core.api.core.comment.application;

import dev.donggi.core.api.core.comment.domain.Comment;
import dev.donggi.core.api.core.comment.domain.CommentRepository;
import dev.donggi.core.api.core.comment.exception.CommentNotFoundException;
import dev.donggi.core.api.core.comment.exception.NoOffsetPageInvalidException;
import dev.donggi.core.api.web.comment.dto.CommentRegisterCommand;
import dev.donggi.core.api.web.comment.dto.NoOffsetPageCommand;
import dev.donggi.core.api.core.question.domain.QuestionRepository;
import dev.donggi.core.api.core.question.domain.exception.QuestionNotFoundException;
import dev.donggi.core.api.core.comment.application.dto.CommentDto;
import dev.donggi.core.api.core.comment.application.dto.CommentPaginationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService implements CommentEditor, CommentFinder {

    private static final Long COMMENT_PAGE_LIMIT_SIZE = 10L;

    private final CommentRepository commentRepository;
    private final QuestionRepository questionRepository;

    @Override
    @Transactional
    public CommentDto save(Long questionId, CommentRegisterCommand commentRegisterCommand) {
        Comment comment = commentRepository.save(commentRegisterCommand.convertToEntity(questionId));
        return CommentDto.ofEntity(comment);
    }

    @Override
    @Transactional
    public void delete(Long questionId, Long commentId, String password) {
        questionRepository.findById(questionId)
            .orElseThrow(QuestionNotFoundException::new);

        Comment comment = commentRepository.findByQuestionIdAndId(questionId, commentId)
            .orElseThrow(CommentNotFoundException::new);

        comment.checkPassword(password);

        commentRepository.delete(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public CommentPaginationDto findAllBy(Long questionId, NoOffsetPageCommand pageCommand) {
        checkNoOffsetPageSize(pageCommand.getSize());

        Long searchAfterId = pageCommand.getSearchAfterId() == 0
            ? commentRepository.findMaxId().orElse(0L)
            : pageCommand.getSearchAfterId();

        Slice<Comment> sliceComments = commentRepository.findAllByQuestionIdAndSearchAfterIdAndPageable(questionId, searchAfterId,
            Pageable.ofSize(Math.toIntExact(pageCommand.getSize())));

        return CommentPaginationDto.ofEntity(sliceComments, questionId);
    }

    private void checkNoOffsetPageSize(Long size) {
        if (size > COMMENT_PAGE_LIMIT_SIZE) {
            throw new NoOffsetPageInvalidException();
        }
    }
}
