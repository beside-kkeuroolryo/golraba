package dev.donggi.core.api.core.question.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface QuestionRepository {

    /**
     * 저장소에서 해당 category의 모든 질문 id를 조회합니다.
     * @param categories 검색할 카테고리
     */
    List<Long> findAllIdsByCategories(List<String> categories);

    /**
     * question 을 저장소에 저장합니다.
     * @param question 저장할 question
     * @return 저장된 question
     */
     Question save(Question question);

    /**
     * 저장소에서 질문을 모두 삭제합니다.
     */
    void deleteAll();

    /**
     * 저장소에서 question 를 검색합니다.
     *
     * @param questionId 검색할 id
     * @return Optional<Question> 객체
     */
    Optional<Question> findById(Long questionId);

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
}
