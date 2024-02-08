package donggi.dev.kkeuroolryo.question.domain;

import java.util.Optional;

public interface QuestionResultRepository {

    /**
     * questionResult 를 저장소에 저장합니다.
     * @param questionResult 저장할 questionResult
     * @return 저장된 QuestionResult
     */
    QuestionResult save(QuestionResult questionResult);

    /**
     * 저장소에서 비관적 락을 걸어 question 를 검색합니다.
     *
     * @param questionId 검색할 id
     * @return Optional<Question> 객체
     */
    Optional<QuestionResult> findByQuestionWithPessimisticLock(Long questionId);
}
