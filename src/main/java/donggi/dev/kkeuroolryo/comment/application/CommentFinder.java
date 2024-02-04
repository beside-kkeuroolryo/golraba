package donggi.dev.kkeuroolryo.comment.application;

import donggi.dev.kkeuroolryo.comment.application.dto.CommentPaginationDto;
import donggi.dev.kkeuroolryo.comment.presentation.dto.NoOffsetPageCommand;

public interface CommentFinder {

    /**
     * 페이지 요청에 따른 모든 댓글을 조회합니다.
     *
     * @param noOffsetPageCommand 페이징 정보가 담긴 객체
     * @return 페이징 된 글 객체
     */
    CommentPaginationDto findAllBy(Long questionId, NoOffsetPageCommand noOffsetPageCommand);
}
