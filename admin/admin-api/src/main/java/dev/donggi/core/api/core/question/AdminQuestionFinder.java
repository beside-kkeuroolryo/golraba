package dev.donggi.core.api.core.question;

import dev.donggi.core.api.core.question.dto.QuestionByCategoryPaginationDto;
import dev.donggi.core.api.core.question.dto.QuestionPaginationDto;
import dev.donggi.core.api.web.comment.dto.NoOffsetPageCommand;

public interface AdminQuestionFinder {

    /**
     * 검색을 통해 질문을 조회합니다.
     *
     * @param content 검색할 content
     * @param pageCommand 페이징 정보가 담긴 객체
     * @return questionPaginationDto 페이징 처리가 된 Question 객체
     */
    QuestionPaginationDto searchByContent(String content, NoOffsetPageCommand pageCommand);

    /**
     * 특정 카테고리의 질문을 조회합니다.
     *
     * @param category 조회할 category
     * @param pageCommand 페이징 정보가 담긴 객체
     * @return questionPaginationDto 페이징 처리가 된 Question 객체
     */
    QuestionByCategoryPaginationDto getQuestionByCategory(String category, NoOffsetPageCommand pageCommand);
}
