package dev.donggi.core.api.core.comment.exception;

import dev.donggi.core.api.common.exception.ErrorCodeAndMessage;
import dev.donggi.core.api.common.exception.GolrabaException;

public class CommentInvalidPasswordException extends GolrabaException {

    public CommentInvalidPasswordException() {
        super(ErrorCodeAndMessage.COMMENT_INVALID_PASSWORD);
    }
}
