package dev.donggi.core.api.core.question.infrastructure;

import dev.donggi.core.api.core.question.domain.Question;
import dev.donggi.core.api.core.question.domain.QuestionRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionJpaRepository extends QuestionRepository, JpaRepository<Question, Long> {

    @Query(value = "select q.id from Question q where q.category in :categories")
    List<Long> findAllIdsByCategories(@Param("categories") List<String> categories);
}
