package donggi.dev.kkeuroolryo.web.question;

import donggi.dev.kkeuroolryo.web.question.dto.QuestionRegisterRequest;
import donggi.dev.kkeuroolryo.web.question.dto.QuestionDto;
import donggi.dev.kkeuroolryo.web.question.dto.RandomQuestionsDto;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/golrabas")
public class QuestionRestController {

    @PostMapping("/question")
    public ResponseEntity<QuestionDto> register(@RequestBody QuestionRegisterRequest request) {
        QuestionDto response = new QuestionDto(1L, request.getContent(),
            request.getChoiceA(), request.getChoiceB());
        return ResponseEntity.created(URI.create("/api/golrabas/question")).body(response);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<RandomQuestionsDto> getRandomQuestionsByCategory(@PathVariable("category") String category) {
        List<QuestionDto> questions = new ArrayList<>();
        questions.add(new QuestionDto(1L, "본문", "선택A", "선택B"));
        RandomQuestionsDto randomQuestionsDto = new RandomQuestionsDto(category, questions);
        return ResponseEntity.ok().body(randomQuestionsDto);
    }
}
