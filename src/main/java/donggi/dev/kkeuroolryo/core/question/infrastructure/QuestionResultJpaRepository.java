package donggi.dev.kkeuroolryo.core.question.infrastructure;

import donggi.dev.kkeuroolryo.core.question.domain.QuestionResult;
import donggi.dev.kkeuroolryo.core.question.domain.QuestionResultRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionResultJpaRepository extends QuestionResultRepository, JpaRepository<QuestionResult, Long> {

}
