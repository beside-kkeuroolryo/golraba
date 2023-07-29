package dev.donggi.core.api.core.comment.exception;

import dev.donggi.core.api.common.exception.ErrorCodeAndMessage;
import dev.donggi.core.api.common.exception.GolrabaException;

public class CommentInvalidContentException extends GolrabaException {

    public CommentInvalidContentException() {
        super(ErrorCodeAndMessage.COMMENT_INVALID_CONTENT);
    }
}
