package donggi.dev.core.api.core.comment.exception;

import donggi.dev.core.api.common.exception.ErrorCodeAndMessage;
import donggi.dev.core.api.common.exception.GolrabaException;

public class CommentNotFoundException extends GolrabaException {

    public CommentNotFoundException() {
        super(ErrorCodeAndMessage.COMMENT_NOT_FOUND);
    }
}
