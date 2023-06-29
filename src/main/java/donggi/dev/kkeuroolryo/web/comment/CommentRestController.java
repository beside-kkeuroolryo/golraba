package donggi.dev.kkeuroolryo.web.comment;

import donggi.dev.kkeuroolryo.core.comment.application.CommentEditor;
import donggi.dev.kkeuroolryo.core.comment.application.dto.CommentDto;
import donggi.dev.kkeuroolryo.web.comment.dto.CommentDeleteCommand;
import donggi.dev.kkeuroolryo.web.comment.dto.CommentRegisterCommand;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/golrabas")
public class CommentRestController {

    private final CommentEditor commentEditor;

    @PostMapping("/{questionId}/comments")
    public ResponseEntity<CommentDto> register(@PathVariable("questionId") Long questionId,
        @RequestBody CommentRegisterCommand commentRegisterCommand) {
        CommentDto commentDto = commentEditor.save(questionId, commentRegisterCommand);
        return ResponseEntity.created(URI.create("/api/golrabas/question")).body(commentDto);
    }

    @DeleteMapping("/{questionId}/comments/{commentId}")
    public ResponseEntity<CommentDto> register(@PathVariable("questionId") Long questionId,
        @PathVariable("commentId") Long commentId, @RequestBody CommentDeleteCommand commentDeleteCommand) {
        commentEditor.delete(questionId, commentId, commentDeleteCommand.getPassword());
        return ResponseEntity.noContent().build();
    }
}
