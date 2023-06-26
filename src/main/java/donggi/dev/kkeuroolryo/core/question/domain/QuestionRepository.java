package donggi.dev.kkeuroolryo.core.question.domain;

import java.util.List;

public interface QuestionRepository {

    /**
     * 저장소에서 해당 category의 모든 질문의 id를 조회합니다.
     * @param category 검색할 카테고리
     */
     List<Long> findAllIdsByCategory(String category);

    /**
     * question 을 저장소에 저장합니다.
     * @param question 저장할 question
     * @return 저장된 question
     */
     Question save(Question question);

    /**
     * 저장소에서 id 리스트의 question을 조회합니다.
     * @param ids 조회할 아이디 리스트
     * @return 조회한 question 리스트
     */
     List<Question> findByIdIn(List<Long> ids);
}
