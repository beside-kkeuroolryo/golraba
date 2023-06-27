package donggi.dev.kkeuroolryo.core.comment.application;

import donggi.dev.kkeuroolryo.core.comment.application.dto.CommentDto;
import donggi.dev.kkeuroolryo.web.comment.dto.CommentRegisterCommand;

public interface CommentEditor {

    /**
     * 사용자가 등록한 질문을 저장소에 저장합니다.
     *
     * @param commentRegisterCommand 댓글 등록 객체
     * @return commentDto 등록된 comment 가 포함된 객체
     */
    CommentDto save(Long questionId, CommentRegisterCommand commentRegisterCommand);
}
