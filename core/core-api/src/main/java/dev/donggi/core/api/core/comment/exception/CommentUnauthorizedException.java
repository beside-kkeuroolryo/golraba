package dev.donggi.core.api.core.comment.exception;

import dev.donggi.core.api.common.exception.ErrorCodeAndMessage;
import dev.donggi.core.api.common.exception.GolrabaException;

public class CommentUnauthorizedException extends GolrabaException {

    public CommentUnauthorizedException() {
        super(ErrorCodeAndMessage.COMMENT_UNAUTHORIZED);
    }
}
