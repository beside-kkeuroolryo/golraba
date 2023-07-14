package donggi.dev.kkeuroolryo.core.comment.exception;

import donggi.dev.kkeuroolryo.common.exception.ErrorCodeAndMessage;
import donggi.dev.kkeuroolryo.common.exception.GolrabaException;

public class CommentInvalidContentException extends GolrabaException {

    public CommentInvalidContentException() {
        super(ErrorCodeAndMessage.COMMENT_INVALID_CONTENT);
    }
}
