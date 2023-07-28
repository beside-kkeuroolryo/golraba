package donggi.dev.core.api.core.comment.exception;

import donggi.dev.core.api.common.exception.ErrorCodeAndMessage;
import donggi.dev.core.api.common.exception.GolrabaException;

public class CommentInvalidContentException extends GolrabaException {

    public CommentInvalidContentException() {
        super(ErrorCodeAndMessage.COMMENT_INVALID_CONTENT);
    }
}
