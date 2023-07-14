package donggi.dev.core.api.core.comment.domain;

import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

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
     * 저장소에서 댓글을 검색합니다.
     *
     * @param commentId 검색할 id
     * @return Optional<Comment> 객체
     */
    Optional<Comment> findById(Long commentId);

    /**
     * 저장소에서 id의 최대값을 찾습니다.
     *
     * @return id 최대값
     */
    Optional<Long> findMaxId();

    /**
     * 저장소에서 특정 질문 id의 댓글을 검색 id 기준 이후의 페이지만큼 조회합니다.
     * @param questionId 질문 id
     * @param searchAfterId 검색 기준 대상
     * @param ofSize 페이지 크기
     * @return 페이징 된 댓글 객체
     */
    Slice<Comment> findAllByQuestionIdAndSearchAfterIdAndPageable(Long questionId, Long searchAfterId, Pageable ofSize);
}
