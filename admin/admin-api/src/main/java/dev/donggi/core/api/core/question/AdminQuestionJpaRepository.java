package dev.donggi.core.api.core.question;

import dev.donggi.core.api.core.question.domain.Question;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdminQuestionJpaRepository extends AdminQuestionRepository, JpaRepository<Question, Long> {

    @Query(value = "select max(q.id) from Question q")
    Optional<Long> findMaxId();

    @Query(value = "select q from Question q where q.content.content like %:content% and q.id <= :searchAfterId order by q.id desc")
    Slice<Question> findByContent(@Param("content") String content, @Param("searchAfterId") Long searchAfterId, Pageable ofSize);
}
