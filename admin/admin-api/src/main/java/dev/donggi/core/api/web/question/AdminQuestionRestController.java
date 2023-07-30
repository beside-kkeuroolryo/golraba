package dev.donggi.core.api.web.question;

import dev.donggi.core.api.common.response.ApiResponse;
import dev.donggi.core.api.core.member.domain.AuthenticatedMember;
import dev.donggi.core.api.core.question.AdminQuestionEditor;
import dev.donggi.core.api.core.question.AdminQuestionFinder;
import dev.donggi.core.api.core.question.application.QuestionFinder;
import dev.donggi.core.api.core.question.application.dto.QuestionDto;
import dev.donggi.core.api.core.question.dto.AdminQuestionDto;
import dev.donggi.core.api.web.comment.dto.NoOffsetPageCommand;
import dev.donggi.core.api.web.member.dto.LoginMember;
import dev.donggi.core.api.core.question.dto.QuestionPaginationDto;
import dev.donggi.core.api.web.question.dto.AdminQuestionRegisterCommand;
import dev.donggi.core.api.web.question.dto.AdminQuestionUpdateCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/question")
public class AdminQuestionRestController {

    private final AdminQuestionFinder adminQuestionFinder;
    private final AdminQuestionEditor adminQuestionEditor;
    private final QuestionFinder questionFinder;

    @GetMapping("/{questionId}")
    public ApiResponse<QuestionDto> getByQuestion(@AuthenticatedMember LoginMember loginMember,
                                                  @PathVariable("questionId") Long questionId) {
        QuestionDto question = questionFinder.getQuestion(questionId);
        return ApiResponse.success(question);
    }

    @GetMapping("/search")
    public ApiResponse<QuestionPaginationDto> searchQuestions(@AuthenticatedMember LoginMember loginMember,
                                                              @RequestParam("content") String content,
                                                              @RequestParam(required = false, defaultValue = "0") String searchAfterId,
                                                              @RequestParam(required = false, defaultValue = "30") String size) {
        QuestionPaginationDto question = adminQuestionFinder.searchByContent(content, new NoOffsetPageCommand(searchAfterId, size));
        return ApiResponse.success(question);
    }

    @PostMapping
    public ApiResponse<AdminQuestionDto> register(@AuthenticatedMember LoginMember loginMember,
                                                  @RequestBody AdminQuestionRegisterCommand questionRegisterCommand) {
        AdminQuestionDto adminQuestionDto = adminQuestionEditor.save(questionRegisterCommand);
        return ApiResponse.success(adminQuestionDto);
    }

    @PutMapping("/{questionId}")
    public ApiResponse<Void> update(@AuthenticatedMember LoginMember loginMember,
                                    @PathVariable("questionId") Long questionId,
                                    @RequestBody AdminQuestionUpdateCommand adminQuestionUpdateCommand) {
        adminQuestionEditor.update(questionId, adminQuestionUpdateCommand);
        return ApiResponse.success();
    }
}
