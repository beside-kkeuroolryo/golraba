package donggi.dev.core.api.web.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentDeleteCommand {

    private String password;

    public CommentDeleteCommand(String password) {
        this.password = password;
    }
}
