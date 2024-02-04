package donggi.dev.kkeuroolryo.question.presentation;

import donggi.dev.kkeuroolryo.comment.presentation.dto.NoOffsetPageCommand;
import donggi.dev.kkeuroolryo.common.response.ApiResponse;
import donggi.dev.kkeuroolryo.question.application.QuestionEditor;
import donggi.dev.kkeuroolryo.question.application.QuestionFinder;
import donggi.dev.kkeuroolryo.question.application.dto.QuestionPaginationDto;
import donggi.dev.kkeuroolryo.question.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/golrabas")
public class AdminQuestionRestController {

    private final QuestionFinder questionFinder;
    private final QuestionEditor questionEditor;

    /**
     * 어드민에서 카테고리로 질문 리스트를 조회합니다.
     */
    @GetMapping("/category/{category}/question")
    public ApiResponse<QuestionPaginationDto> getQuestionsByCategory(
        @PathVariable("category") Category category,
        @RequestParam(required = false, defaultValue = "0") String searchAfterId,
        @RequestParam(required = false, defaultValue = "20") String size
    ) {
        QuestionPaginationDto questionPaginationDto = questionFinder.findAllByCategory(category, new NoOffsetPageCommand(searchAfterId, size));
        return ApiResponse.success(questionPaginationDto);
    }

    /**
     * 어드민에서 특정 active 상태의 카테고리 질문 리스트를 조회합니다.
     */
    @GetMapping("/category/{category}/question/active/{active}")
    public ApiResponse<QuestionPaginationDto> getQuestionsByCategoryAndActive(
        @PathVariable("category") Category category,
        @PathVariable("active") boolean active,
        @RequestParam(required = false, defaultValue = "0") String searchAfterId,
        @RequestParam(required = false, defaultValue = "20") String size
    ) {
        QuestionPaginationDto questionPaginationDto = questionFinder.findAllByCategoryAndActive(category, active, new NoOffsetPageCommand(searchAfterId, size));
        return ApiResponse.success(questionPaginationDto);
    }

    @GetMapping("/question")
    public ApiResponse<QuestionPaginationDto> getAll(
        @RequestParam(required = false, defaultValue = "0") String searchAfterId,
        @RequestParam(required = false, defaultValue = "20") String size
    ) {
        QuestionPaginationDto questionPaginationDto = questionFinder.findAllIdsByCategory(new NoOffsetPageCommand(searchAfterId, size));
        return ApiResponse.success(questionPaginationDto);
    }

    @GetMapping("/question/search")
    public ApiResponse<QuestionPaginationDto> search(
        @RequestParam String keyword,
        @RequestParam(required = false, defaultValue = "0") String searchAfterId,
        @RequestParam(required = false, defaultValue = "20") String size
    ) {
        QuestionPaginationDto questionPaginationDto = questionFinder.search(keyword, new NoOffsetPageCommand(searchAfterId, size));
        return ApiResponse.success(questionPaginationDto);
    }

    @PatchMapping("/question/{questionId}/active/{active}")
    public ApiResponse<Void> changeActive(@PathVariable("questionId") Long questionId,
        @PathVariable("active") boolean active) {
        questionEditor.changeActive(questionId, active);
        return ApiResponse.success();
    }
}
