package donggi.dev.kkeuroolryo.core.question.domain;

public interface QuestionResultRepository {

    /**
     * questionResult 를 저장소에 저장합니다.
     * @param questionResult 저장할 questionResult
     * @return 저장된 QuestionResult
     */
    QuestionResult save(QuestionResult questionResult);
}
