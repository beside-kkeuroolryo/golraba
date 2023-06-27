package donggi.dev.kkeuroolryo.web.question;

import donggi.dev.kkeuroolryo.core.question.application.QuestionEditor;
import donggi.dev.kkeuroolryo.core.question.application.QuestionFinder;
import donggi.dev.kkeuroolryo.core.question.application.dto.QuestionDto;
import donggi.dev.kkeuroolryo.web.question.dto.QuestionRegisterCommand;
import donggi.dev.kkeuroolryo.core.question.application.dto.RandomQuestionsDto;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<QuestionDto> register(@RequestBody QuestionRegisterCommand questionRegisterCommand) {
        QuestionDto questionDto = questionEditor.save(questionRegisterCommand);
        return ResponseEntity.created(URI.create("/api/golrabas/question")).body(questionDto);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<RandomQuestionsDto> getQuestionsByCategory(@PathVariable("category") String category) {
        RandomQuestionsDto randomQuestionsDto = questionFinder.getRandomQuestionsByCategory(category);
        return ResponseEntity.ok().body(randomQuestionsDto);
    }
}
