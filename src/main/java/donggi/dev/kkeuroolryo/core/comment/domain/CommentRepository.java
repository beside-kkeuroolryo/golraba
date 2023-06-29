package donggi.dev.kkeuroolryo.core.comment.domain;

import java.util.Optional;

public interface CommentRepository {

    /**
     * comment 를 저장소에 저장합니다.
     * @param comment 저장할 comment
     * @return 저장된 comment
     */
    Comment save(Comment comment);

    /**
     * 저장소에서 comment 를 검색합니다.
     *
     * @param commentId 검색할 id
     * @return Optional<Comment> 객체
     */
    Optional<Comment> findByQuestionIdAndId(Long questionId, Long commentId);

    /**
     * 저장소에서 특정 댓글을 삭제합니다.
     *
     * @param comment 삭제할 글 객체
     */
    void delete(Comment comment);

    /**
     * 저장소에서 댓글을 모두 삭제합니다.
     */
    void deleteAll();

    /**
     * 저장소에서 comment 를 검색합니다.
     *
     * @param commentId 검색할 id
     * @return Optional<Post> 객체
     */
    Optional<Comment> findById(Long commentId);
}
