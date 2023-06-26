package donggi.dev.kkeuroolryo.web.question;

import donggi.dev.kkeuroolryo.web.question.dto.QuestionRegisterRequest;
import donggi.dev.kkeuroolryo.web.question.dto.QuestionRegisterResponse;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/golrabas")
public class QuestionRestController {

    @PostMapping("/question")
    public ResponseEntity<QuestionRegisterResponse> register(@RequestBody QuestionRegisterRequest request) {
        QuestionRegisterResponse response = new QuestionRegisterResponse(1L, request.getContent(),
            request.getChoiceA(), request.getChoiceB());
        return ResponseEntity.created(URI.create("/api/golrabas/question")).body(response);
    }
}
