package donggi.dev.kkeuroolryo.comment.application.dto;

import donggi.dev.kkeuroolryo.comment.domain.Comment;
import lombok.Getter;

@Getter
public class CommentDto {

    private Long id;
    private String username;
    private String content;

    public static CommentDto ofEntity(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.id = comment.getId();
        commentDto.username = comment.getUsername().getUsername();
        commentDto.content = comment.getContent().getContent();
        return commentDto;
    }
}
