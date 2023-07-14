package donggi.dev.kkeuroolryo.core.comment.exception;

import donggi.dev.kkeuroolryo.common.exception.ErrorCodeAndMessage;
import donggi.dev.kkeuroolryo.common.exception.GolrabaException;

public class CommentInvalidPasswordException extends GolrabaException {

    public CommentInvalidPasswordException() {
        super(ErrorCodeAndMessage.COMMENT_INVALID_PASSWORD);
    }
}
