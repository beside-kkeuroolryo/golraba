package dev.donggi.core.api.core.question;

import dev.donggi.core.api.core.question.application.dto.QuestionDto;
import dev.donggi.core.api.core.question.dto.QuestionPaginationDto;
import dev.donggi.core.api.web.comment.dto.NoOffsetPageCommand;

public interface AdminQuestionFinder {

    /**
     * 검색을 통해 질문을 조회합니다.
     *
     * @param content 검색할 content
     * @return QuestionPaginationDto 페이징 처리가 된 Question 객체
     */
    QuestionPaginationDto searchByContent(String content, NoOffsetPageCommand pageCommand);
}
