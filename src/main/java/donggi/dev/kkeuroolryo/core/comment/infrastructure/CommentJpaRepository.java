package donggi.dev.kkeuroolryo.core.comment.infrastructure;

import donggi.dev.kkeuroolryo.core.comment.domain.Comment;
import donggi.dev.kkeuroolryo.core.comment.domain.CommentRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends CommentRepository, JpaRepository<Comment, Long> {

}
