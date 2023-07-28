package donggi.dev.core.api.core.comment.exception;

import donggi.dev.core.api.common.exception.ErrorCodeAndMessage;
import donggi.dev.core.api.common.exception.GolrabaException;

public class CommentInvalidUsernameException extends GolrabaException {

    public CommentInvalidUsernameException() {
        super(ErrorCodeAndMessage.QUESTION_INVALID_CHOICE);
    }
}
