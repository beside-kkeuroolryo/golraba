package donggi.dev.kkeuroolryo.core.question.application;

import donggi.dev.kkeuroolryo.core.question.application.dto.QuestionDto;
import donggi.dev.kkeuroolryo.core.question.application.dto.QuestionPaginationDto;
import donggi.dev.kkeuroolryo.core.question.application.dto.RandomQuestionsDto;
import donggi.dev.kkeuroolryo.core.question.domain.Category;
import donggi.dev.kkeuroolryo.web.comment.dto.NoOffsetPageCommand;

public interface QuestionFinder {

    /**
     * 특정 카테고리의 질문을 무작위로 조회합니다.
     *
     * @param category 카테고리 이름
     * @return RandomQuestionsDto 무작위로 조회한 여러 Question 이 포함된 객체
     */
    RandomQuestionsDto getRandomQuestionsByCategory(Category category);

    /**
     * 특정 질문을 조회합니다.
     *
     * @param questionId 질문 id
     * @return QuestionDto 조회한 Question 이 포함된 객체
     */
    QuestionDto getQuestion(Long questionId);

    /**
     * 페이지 요청에 따른 모든 질문을 조회합니다.
     *
     * @param noOffsetPageCommand 페이징 정보가 담긴 객체
     * @return 페이징된 질문 객체
     */
    QuestionPaginationDto findAllIdsByCategory(NoOffsetPageCommand noOffsetPageCommand);

    /**
     * 질문을 키워드로 검색하여 리스트로 조회합니다.
     * @param keyword 검색할 키워드
     * @param noOffsetPageCommand 페이징 정보가 담긴 객체
     * @return 페이징된 질문 객체
     */
    QuestionPaginationDto search(String keyword, NoOffsetPageCommand noOffsetPageCommand);

    /**
     * 어드민에서 카테고리로 질문 리스트를 조회합니다.
     * @param category 조회할 카테고리
     * @param pageCommand 페이징 정보가 담긴 객체
     * @return 페이징된 질문 객체
     */
    QuestionPaginationDto findAllByCategory(Category category, NoOffsetPageCommand pageCommand);

    /**
     * 어드민에서 특정 active 상태의 카테고리 질문 리스트를 조회합니다.
     * @param category 조회할 카테고리
     * @param active 조회할 active 상태
     * @param pageCommand 페이징 정보가 담긴 객체
     * @return 페이징된 질문 객체
     */
    QuestionPaginationDto findAllByCategoryAndActive(Category category, boolean active, NoOffsetPageCommand pageCommand);
}
