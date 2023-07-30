package dev.donggi.core.api.core.question;

import dev.donggi.core.api.core.comment.domain.Comment;
import dev.donggi.core.api.core.question.domain.Question;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface AdminQuestionRepository {

    /**
     * 저장소에서 id의 최대값을 찾습니다.
     *
     * @return id 최대값
     */
    Optional<Long> findMaxId();

    /**
     * 저장소에서 검색 키워드를 통해 question 를 검색합니다.
     *
     * @param content 검색할 키워드
     * @param searchAfterId 검색 기준 대상
     * @param ofSize 페이지 크기
     * @return 페이징 된 질문 객체
     */
    Slice<Question> findByContent(String content, Long searchAfterId, Pageable ofSize);

    /**
     * 저장소에서 질문을 삭제합니다.
     *
     * @param question 삭제할 질문 객체
     */
    void delete(Question question);
}
