package donggi.dev.kkeuroolryo.core.comment.domain.exception;

import donggi.dev.kkeuroolryo.common.exception.ErrorCodeAndMessage;
import donggi.dev.kkeuroolryo.common.exception.GolrabaException;

public class CommentNotFoundException extends GolrabaException {

    public CommentNotFoundException() {
        super(ErrorCodeAndMessage.COMMENT_NOT_FOUND);
    }
}
