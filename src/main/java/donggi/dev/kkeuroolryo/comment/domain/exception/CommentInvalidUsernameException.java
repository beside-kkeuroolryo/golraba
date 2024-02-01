package donggi.dev.kkeuroolryo.comment.domain.exception;

import donggi.dev.kkeuroolryo.common.exception.ErrorCodeAndMessage;
import donggi.dev.kkeuroolryo.common.exception.GolrabaException;

public class CommentInvalidUsernameException extends GolrabaException {

    public CommentInvalidUsernameException() {
        super(ErrorCodeAndMessage.QUESTION_INVALID_CHOICE);
    }
}
