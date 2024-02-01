package donggi.dev.kkeuroolryo.question.presentation;

import donggi.dev.kkeuroolryo.common.response.ApiResponse;
import donggi.dev.kkeuroolryo.question.application.QuestionEditor;
import donggi.dev.kkeuroolryo.question.application.QuestionFinder;
import donggi.dev.kkeuroolryo.question.application.dto.QuestionDto;
import donggi.dev.kkeuroolryo.question.application.dto.RandomQuestionsDto;
import donggi.dev.kkeuroolryo.question.domain.Category;
import donggi.dev.kkeuroolryo.question.dto.QuestionRegisterDto;
import donggi.dev.kkeuroolryo.question.dto.QuestionResultCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/category/{category}")
    public ApiResponse<RandomQuestionsDto> getQuestionIdsByCategory(@PathVariable("category") Category category) {
        RandomQuestionsDto randomQuestionsDto = questionFinder.getRandomQuestionsByCategory(category);
        return ApiResponse.success(randomQuestionsDto);
    }

    @GetMapping("/question/{questionId}")
    public ApiResponse<QuestionDto> getQuestion(@PathVariable("questionId") Long questionId) {
        QuestionDto questionDto = questionFinder.getQuestion(questionId);
        return ApiResponse.success(questionDto);
    }
}
