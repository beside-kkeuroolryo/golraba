package dev.donggi.core.api.web;

import dev.donggi.core.api.common.response.ApiResponse;
import dev.donggi.core.api.core.member.domain.AuthenticatedMember;
import dev.donggi.core.api.core.question.application.QuestionService;
import dev.donggi.core.api.core.question.application.dto.QuestionDto;
import dev.donggi.core.api.web.comment.dto.NoOffsetPageCommand;
import dev.donggi.core.api.web.member.dto.LoginMember;
import dev.donggi.core.api.core.question.application.dto.QuestionPaginationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminQuestionRestController {

    private final QuestionService questionService;

    @GetMapping("/question/{questionId}")
    public ApiResponse<QuestionDto> getByQuestion(@AuthenticatedMember LoginMember loginMember,
                                                  @PathVariable("questionId") Long questionId) {
        QuestionDto question = questionService.getQuestion(questionId);
        return ApiResponse.success(question);
    }

    @GetMapping("/question/search")
    public ApiResponse<QuestionPaginationDto> searchQuestions(@AuthenticatedMember LoginMember loginMember,
                                                              @RequestParam("content") String content,
                                                              @RequestParam(required = false, defaultValue = "0") String searchAfterId,
                                                              @RequestParam(required = false, defaultValue = "30") String size) {
        QuestionPaginationDto question = questionService.searchByContent(content, new NoOffsetPageCommand(searchAfterId, size));
        return ApiResponse.success(question);
    }
}
