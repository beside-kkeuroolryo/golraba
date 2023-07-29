package dev.donggi.core.api.core.comment.exception;

import dev.donggi.core.api.common.exception.ErrorCodeAndMessage;
import dev.donggi.core.api.common.exception.GolrabaException;

public class CommentNotFoundException extends GolrabaException {

    public CommentNotFoundException() {
        super(ErrorCodeAndMessage.COMMENT_NOT_FOUND);
    }
}
