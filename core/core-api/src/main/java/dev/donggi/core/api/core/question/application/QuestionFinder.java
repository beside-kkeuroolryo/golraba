package dev.donggi.core.api.core.question.application;

import dev.donggi.core.api.core.question.application.dto.QuestionDto;
import dev.donggi.core.api.core.question.application.dto.QuestionPaginationDto;
import dev.donggi.core.api.core.question.application.dto.RandomQuestionsDto;
import dev.donggi.core.api.web.comment.dto.NoOffsetPageCommand;

public interface QuestionFinder {

    /**
     * 특정 카테고리의 질문을 무작위로 조회합니다.
     *
     * @param category 카테고리 이름
     * @return RandomQuestionsDto 무작위로 조회한 여러 Question 이 포함된 객체
     */
    RandomQuestionsDto getRandomQuestionsByCategory(String category);

    /**
     * 특정 질문을 조회합니다.
     *
     * @param questionId 질문 id
     * @return QuestionDto 조회한 Question 이 포함된 객체
     */
    QuestionDto getQuestion(Long questionId);

    /**
     * 검색을 통해 질문을 조회합니다.
     *
     * @param content 검색할 content
     * @return QuestionPaginationDto 페이징 처리가 된 Question 객체
     */
    QuestionPaginationDto searchByContent(String content, NoOffsetPageCommand pageCommand);
}
