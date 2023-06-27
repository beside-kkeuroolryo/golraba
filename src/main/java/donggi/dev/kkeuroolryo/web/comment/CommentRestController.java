package donggi.dev.kkeuroolryo.web.comment;

import donggi.dev.kkeuroolryo.core.comment.application.dto.CommentDto;
import donggi.dev.kkeuroolryo.core.question.application.QuestionEditor;
import donggi.dev.kkeuroolryo.core.question.application.QuestionFinder;
import donggi.dev.kkeuroolryo.core.question.application.dto.QuestionDto;
import donggi.dev.kkeuroolryo.core.question.application.dto.RandomQuestionsDto;
import donggi.dev.kkeuroolryo.web.comment.dto.CommentRegisterCommand;
import donggi.dev.kkeuroolryo.web.question.dto.QuestionRegisterCommand;
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
public class CommentRestController {

    @PostMapping("/{questionId}/comments")
    public ResponseEntity<CommentDto> register(@PathVariable("questionId") Long questionId,
        @RequestBody CommentRegisterCommand commentRegisterCommand) {
        CommentDto dummyDto = new CommentDto(1L, "사용자 이름", "댓글 본문");
        return ResponseEntity.created(URI.create("/api/golrabas/question")).body(dummyDto);
    }
}
