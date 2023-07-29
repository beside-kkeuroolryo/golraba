package dev.donggi.core.api.core.comment.application.dto;

import dev.donggi.core.api.core.comment.domain.Comment;
import dev.donggi.core.api.core.common.PageDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import org.springframework.data.domain.Slice;

@Getter
public class CommentPaginationDto {

    private Long questionId;
    private List<CommentDto> comments = new ArrayList<>();
    private PageDto page;

    public static CommentPaginationDto ofEntity(Slice<Comment> sliceComment, Long questionId) {
        CommentPaginationDto commentPaginationDto = new CommentPaginationDto();
        commentPaginationDto.questionId = questionId;

        List<Comment> comments = sliceComment.getContent();
        commentPaginationDto.comments = comments.stream()
            .map(CommentDto::ofEntity)
            .collect(Collectors.toList());

        int postsSize = comments.size();
        long nextId = comments.isEmpty() ? 0L : comments.get(postsSize - 1).getId();
        commentPaginationDto.page = new PageDto(postsSize, nextId, sliceComment.isLast());

        return commentPaginationDto;
    }
}
