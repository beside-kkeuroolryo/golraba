package donggi.dev.kkeuroolryo.web.comment.dto;

import donggi.dev.kkeuroolryo.core.comment.domain.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRegisterCommand {

    private String username;
    private String password;
    private String content;

    public CommentRegisterCommand(String username, String password, String content) {
        this.username = username;
        this.password = password;
        this.content = content;
    }

    public Comment convertToEntity(Long questionId) {
        return new Comment(questionId, getUsername(), getPassword(), getContent());
    }
}
