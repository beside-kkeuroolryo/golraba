package donggi.dev.kkeuroolryo.web.question;

import donggi.dev.kkeuroolryo.common.response.ApiResponse;
import donggi.dev.kkeuroolryo.core.question.application.QuestionEditor;
import donggi.dev.kkeuroolryo.core.question.application.QuestionFinder;
import donggi.dev.kkeuroolryo.core.question.application.dto.QuestionDto;
import donggi.dev.kkeuroolryo.core.question.application.dto.QuestionPaginationDto;
import donggi.dev.kkeuroolryo.core.question.application.dto.RandomQuestionsDto;
import donggi.dev.kkeuroolryo.web.comment.dto.NoOffsetPageCommand;
import donggi.dev.kkeuroolryo.web.question.dto.QuestionActiveUpdateDto;
import donggi.dev.kkeuroolryo.web.question.dto.QuestionCategoryRequest;
import donggi.dev.kkeuroolryo.web.question.dto.QuestionRegisterDto;
import donggi.dev.kkeuroolryo.web.question.dto.QuestionResultCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/golrabas")
public class QuestionRestController {

    private final QuestionFinder questionFinder;
    private final QuestionEditor questionEditor;

    @PostMapping("/question")
    public ApiResponse<QuestionDto> register(@RequestBody QuestionRegisterDto questionRegisterDto) {
        QuestionDto questionDto = questionEditor.save(questionRegisterDto);
        return ApiResponse.success(questionDto);
    }

    @PutMapping("/question/{questionId}")
    public ApiResponse<Void> modify(@PathVariable("questionId") Long questionId,
                                    @RequestBody QuestionRegisterDto questionRegisterDto) {
        questionEditor.modify(questionId, questionRegisterDto);
        return ApiResponse.success();
    }

    @PostMapping("/result")
    public ApiResponse<Void> register(@RequestBody QuestionResultCommand resultCommand) {
        questionEditor.result(resultCommand);
        return ApiResponse.success();
    }

    /**
     * 카테고리 이름으로 해당 카테고리의 질문을 조회합니다.
     * 질문은 랜덤한 순서로 조회합니다.
     */
    @GetMapping("/category/{category}")
    public ApiResponse<RandomQuestionsDto> getQuestionsByCategory(@PathVariable("category") QuestionCategoryRequest request) {
        RandomQuestionsDto randomQuestionsDto = questionFinder.getRandomQuestionsByCategory(request.category());
        return ApiResponse.success(randomQuestionsDto);
    }

    @GetMapping("/{questionId}")
    public ApiResponse<QuestionDto> getQuestion(@PathVariable("questionId") Long questionId) {
        QuestionDto questionDto = questionFinder.getQuestion(questionId);
        return ApiResponse.success(questionDto);
    }

    @GetMapping("/question")
    public ApiResponse<QuestionPaginationDto> getAll(
        @RequestParam(required = false, defaultValue = "0") String searchAfterId,
        @RequestParam(required = false, defaultValue = "20") String size
    ) {
        QuestionPaginationDto questionPaginationDto = questionFinder.findAllBy(new NoOffsetPageCommand(searchAfterId, size));
        return ApiResponse.success(questionPaginationDto);
    }

    @PatchMapping("/{questionId}/active")
    public ApiResponse<Void> changeActive(@PathVariable("questionId") Long questionId,
                                          @RequestBody QuestionActiveUpdateDto request) {
        questionEditor.changeActive(questionId, request);
        return ApiResponse.success();
    }
}
