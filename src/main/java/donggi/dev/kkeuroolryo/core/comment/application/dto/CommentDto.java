package donggi.dev.kkeuroolryo.core.comment.application.dto;

import lombok.Getter;

@Getter
public class CommentDto {

    private Long id;
    private String username;
    private String content;

    public CommentDto(Long id, String username, String content) {
        this.id = id;
        this.username = username;
        this.content = content;
    }
}
