package donggi.dev.kkeuroolryo.core.question.infrastructure;

import donggi.dev.kkeuroolryo.core.question.domain.QuestionResult;
import donggi.dev.kkeuroolryo.core.question.domain.QuestionResultRepository;
import jakarta.persistence.LockModeType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionResultJpaRepository extends QuestionResultRepository, JpaRepository<QuestionResult, Long> {

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query("select qr from QuestionResult qr where qr.question.id = :id")
    Optional<QuestionResult> findByQuestionWithPessimisticLock(@Param("id") Long id);
}
