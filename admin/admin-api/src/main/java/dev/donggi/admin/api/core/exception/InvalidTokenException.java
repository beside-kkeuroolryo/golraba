package dev.donggi.admin.api.core.exception;

import donggi.dev.core.api.common.exception.ErrorCodeAndMessage;
import donggi.dev.core.api.common.exception.GolrabaException;

public class InvalidTokenException extends GolrabaException {

    public InvalidTokenException() {
        super(ErrorCodeAndMessage.INVALID_TOKEN);
    }
}
