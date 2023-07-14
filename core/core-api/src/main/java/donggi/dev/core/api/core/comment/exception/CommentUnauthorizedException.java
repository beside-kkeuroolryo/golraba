package donggi.dev.core.api.core.comment.exception;

import donggi.dev.core.api.common.exception.ErrorCodeAndMessage;
import donggi.dev.core.api.common.exception.GolrabaException;

public class CommentUnauthorizedException extends GolrabaException {

    public CommentUnauthorizedException() {
        super(ErrorCodeAndMessage.COMMENT_UNAUTHORIZED);
    }
}
