package donggi.dev.kkeuroolryo.core.comment.application;

import donggi.dev.kkeuroolryo.core.comment.application.dto.CommentDto;
import donggi.dev.kkeuroolryo.core.comment.application.dto.CommentPaginationDto;
import donggi.dev.kkeuroolryo.core.comment.domain.Comment;
import donggi.dev.kkeuroolryo.core.comment.domain.CommentRepository;
import donggi.dev.kkeuroolryo.core.comment.exception.CommentNotFoundException;
import donggi.dev.kkeuroolryo.core.comment.exception.NoOffsetPageInvalidException;
import donggi.dev.kkeuroolryo.core.question.domain.QuestionRepository;
import donggi.dev.kkeuroolryo.core.question.domain.exception.QuestionNotFoundException;
import donggi.dev.kkeuroolryo.web.comment.dto.CommentRegisterCommand;
import donggi.dev.kkeuroolryo.web.comment.dto.NoOffsetPageCommand;
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