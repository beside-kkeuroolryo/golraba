package donggi.dev.kkeuroolryo.core.question.infrastructure;

import donggi.dev.kkeuroolryo.core.question.domain.Category;
import donggi.dev.kkeuroolryo.core.question.domain.Question;
import donggi.dev.kkeuroolryo.core.question.domain.QuestionRepository;
import donggi.dev.kkeuroolryo.core.question.domain.exception.QuestionNotFoundException;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionJpaRepository extends QuestionRepository, JpaRepository<Question, Long> {

    // TODO : active = true인 질문만 조회하도록 변경 필요
    @Query(value = "select q.id from Question q where q.category in :categories")
    List<Long> findAllByIdInCategories(@Param("categories") List<Category> categories);

    @Override
    default Question getById(Long questionId) {
        return findById(questionId).orElseThrow(QuestionNotFoundException::new);
    }
}
