package donggi.dev.kkeuroolryo.comment.domain.exception;

import donggi.dev.kkeuroolryo.common.exception.ErrorCodeAndMessage;
import donggi.dev.kkeuroolryo.common.exception.GolrabaException;

public class CommentInvalidContentException extends GolrabaException {

    public CommentInvalidContentException() {
        super(ErrorCodeAndMessage.COMMENT_INVALID_CONTENT);
    }
}
