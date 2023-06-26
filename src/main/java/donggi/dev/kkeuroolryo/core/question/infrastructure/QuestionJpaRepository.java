package donggi.dev.kkeuroolryo.core.question.infrastructure;

import donggi.dev.kkeuroolryo.core.question.domain.Question;
import donggi.dev.kkeuroolryo.core.question.domain.QuestionRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionJpaRepository extends QuestionRepository, JpaRepository<Question, Long> {

    @Query(value = "select q.id from Question q where q.category= :category")
    List<Long> findAllIdsByCategory(@Param("category") String category);

    List<Question> findByIdIn(List<Long> ids);
}
