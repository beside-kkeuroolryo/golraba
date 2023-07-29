package dev.donggi.core.api.web;

import dev.donggi.core.api.common.response.ApiResponse;
import dev.donggi.core.api.core.domain.AuthenticatedMember;
import dev.donggi.core.api.core.question.application.QuestionService;
import dev.donggi.core.api.core.question.application.dto.QuestionDto;
import dev.donggi.core.api.web.dto.LoginMember;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminRestController {

    private final QuestionService questionService;

    @GetMapping("/question/{questionId}")
    public ApiResponse<QuestionDto> getByQuestion(@AuthenticatedMember LoginMember loginMember,
                                                  @PathVariable("questionId") Long questionId) {
        QuestionDto question = questionService.getQuestion(questionId);
        return ApiResponse.success(question);
    }
}
