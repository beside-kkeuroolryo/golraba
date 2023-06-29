package donggi.dev.kkeuroolryo.core.comment.application;

import donggi.dev.kkeuroolryo.core.comment.application.dto.CommentDto;
import donggi.dev.kkeuroolryo.core.comment.domain.Comment;
import donggi.dev.kkeuroolryo.core.comment.domain.CommentRepository;
import donggi.dev.kkeuroolryo.core.comment.domain.exception.CommentNotFoundException;
import donggi.dev.kkeuroolryo.core.question.domain.QuestionRepository;
import donggi.dev.kkeuroolryo.core.question.domain.exception.QuestionNotFoundException;
import donggi.dev.kkeuroolryo.web.comment.dto.CommentRegisterCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService implements CommentEditor {

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
}
