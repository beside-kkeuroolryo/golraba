package donggi.dev.core.api.web.question;

import donggi.dev.core.api.core.question.application.QuestionFinder;
import donggi.dev.core.api.core.question.application.dto.QuestionDto;
import donggi.dev.core.api.core.question.application.dto.RandomQuestionsDto;
import donggi.dev.core.api.common.response.ApiResponse;
import donggi.dev.core.api.core.question.application.QuestionEditor;
import donggi.dev.core.api.web.question.dto.QuestionCategoryRequest;
import donggi.dev.core.api.web.question.dto.QuestionRegisterCommand;
import donggi.dev.core.api.web.question.dto.QuestionResultCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/golrabas")
public class QuestionRestController {

    private final QuestionFinder questionFinder;
    private final QuestionEditor questionEditor;

    @PostMapping("/question")
    public ApiResponse<QuestionDto> register(@RequestBody QuestionRegisterCommand questionRegisterCommand) {
        QuestionDto questionDto = questionEditor.save(questionRegisterCommand);
        return ApiResponse.success(questionDto);
    }

    @PostMapping("/result")
    public ApiResponse<Void> register(@RequestBody QuestionResultCommand resultCommand) {
        questionEditor.result(resultCommand);
        return ApiResponse.success();
    }

    @GetMapping("/category/{category}")
    public ApiResponse<RandomQuestionsDto> getQuestionsByCategory(@PathVariable("category") QuestionCategoryRequest request) {
        RandomQuestionsDto randomQuestionsDto = questionFinder.getRandomQuestionsByCategory(request.getCategory());
        return ApiResponse.success(randomQuestionsDto);
    }

    @GetMapping("/{questionId}")
    public ApiResponse<QuestionDto> getQuestion(@PathVariable("questionId") Long questionId) {
        QuestionDto questionDto = questionFinder.getQuestion(questionId);
        return ApiResponse.success(questionDto);
    }
}
