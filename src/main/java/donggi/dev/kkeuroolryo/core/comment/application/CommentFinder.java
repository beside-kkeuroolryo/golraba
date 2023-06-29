package donggi.dev.kkeuroolryo.core.comment.application;

import donggi.dev.kkeuroolryo.core.comment.application.dto.CommentPaginationDto;
import donggi.dev.kkeuroolryo.web.comment.dto.NoOffsetPageCommand;

public interface CommentFinder {

    /**
     * 페이지 요청에 따른 모든 댓글을 조회합니다.
     *
     * @param noOffsetPageCommand 페이징 정보가 담긴 객체
     * @return 페이징 된 글 객체
     */
    CommentPaginationDto findAllBy(Long questionId, NoOffsetPageCommand noOffsetPageCommand);
}
