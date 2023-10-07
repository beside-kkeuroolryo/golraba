package donggi.dev.kkeuroolryo.core.question.infrastructure;

import donggi.dev.kkeuroolryo.core.question.domain.Category;
import donggi.dev.kkeuroolryo.core.question.domain.Question;
import donggi.dev.kkeuroolryo.core.question.domain.QuestionRepository;
import donggi.dev.kkeuroolryo.core.question.domain.exception.QuestionNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionJpaRepository extends QuestionRepository, JpaRepository<Question, Long> {

    // TODO : active = true인 질문만 조회하도록 변경 필요
    @Query(value = "select q.id from Question q where q.category in :categories and q.active = true")
    List<Long> findAllByIdInCategories(@Param("categories") List<Category> categories);

    @Query(value = "select max(q.id) from Question q ")
    Optional<Long> findMaxId();

    @Query(value = "select q from Question q where q.id <= :searchAfterId and q.active = true order by q.id desc")
    Slice<Question> findAllBySearchAfterIdAndPageable(@Param("searchAfterId") Long searchAfterId, Pageable ofSize);

    @Query(value = "select q from Question q where q.content.content like %:keyword% and q.id <= :searchAfterId and q.active = true order by q.id desc")
    Slice<Question> findAllByContentContainingAndSearchAfterIdAndPageable(@Param("keyword") String keyword, @Param("searchAfterId") Long searchAfterId, Pageable ofSize);

    @Override
    default Question getById(Long questionId) {
        return findById(questionId).orElseThrow(QuestionNotFoundException::new);
    }

    default Long getMaxId() {
        return findMaxId().orElse(0L);
    }
}
