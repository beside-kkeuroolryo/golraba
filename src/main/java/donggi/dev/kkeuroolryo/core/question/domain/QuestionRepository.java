package donggi.dev.kkeuroolryo.core.question.domain;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface QuestionRepository {

    /**
     * 저장소에서 해당 category의 모든 질문 id를 조회합니다.
     * @param categories 검색할 카테고리
     */
    List<Long> findAllByIdInCategories(List<Category> categories);

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
     * @return Question 객체
     */
    Question getById(Long questionId);

    /**
     * 저장소에서 id의 최대값을 찾습니다.
     *
     * @return id 최대값
     */
    Long getMaxId();

    /**
     * 저장소에서 질문을 검색 id 기준 이후의 페이지만큼 조회합니다.
     * @param searchAfterId 검색 기준 대상
     * @param ofSize 페이지 크기
     * @return 페이징 된 질문 객체
     */
    Slice<Question> findAllBySearchAfterIdAndPageable(Long searchAfterId, Pageable ofSize);

    /**
     * 저장소에 id로 해당 질문이 존재하는지 여부를 반환합니다.
     * @param questionId 검색할 질문 id
     * @return 존재하면 true, 존재하지 않으면 false 반환
     */
    boolean existsById(Long questionId);
}
