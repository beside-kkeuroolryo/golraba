package donggi.dev.kkeuroolryo.core.comment.infrastructure;

import donggi.dev.kkeuroolryo.core.comment.domain.Comment;
import donggi.dev.kkeuroolryo.core.comment.domain.CommentRepository;
import donggi.dev.kkeuroolryo.core.comment.domain.exception.CommentNotFoundException;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentJpaRepository extends CommentRepository, JpaRepository<Comment, Long> {

    @Query(value = "select max(c.id) from Comment c ")
    Optional<Long> findMaxId();

    @Query(value = "select c from Comment c where c.questionId = :questionId and c.id <= :searchAfterId order by c.id desc")
    Slice<Comment> findAllByQuestionIdAndSearchAfterIdAndPageable(@Param("questionId") Long questionId, @Param("searchAfterId") Long searchAfterId, Pageable ofSize);

    default Comment getById(Long commentId) {
        return findById(commentId).orElseThrow(CommentNotFoundException::new);
    }
}
