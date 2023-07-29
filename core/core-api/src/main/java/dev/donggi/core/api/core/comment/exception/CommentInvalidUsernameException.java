package dev.donggi.core.api.core.comment.exception;

import dev.donggi.core.api.common.exception.ErrorCodeAndMessage;
import dev.donggi.core.api.common.exception.GolrabaException;

public class CommentInvalidUsernameException extends GolrabaException {

    public CommentInvalidUsernameException() {
        super(ErrorCodeAndMessage.QUESTION_INVALID_CHOICE);
    }
}
