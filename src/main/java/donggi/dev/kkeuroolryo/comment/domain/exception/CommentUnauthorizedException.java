package donggi.dev.kkeuroolryo.comment.domain.exception;

import donggi.dev.kkeuroolryo.common.exception.ErrorCodeAndMessage;
import donggi.dev.kkeuroolryo.common.exception.GolrabaException;

public class CommentUnauthorizedException extends GolrabaException {

    public CommentUnauthorizedException() {
        super(ErrorCodeAndMessage.COMMENT_UNAUTHORIZED);
    }
}
