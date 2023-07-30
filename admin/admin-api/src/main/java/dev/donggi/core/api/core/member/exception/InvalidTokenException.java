package dev.donggi.core.api.core.member.exception;

import dev.donggi.core.api.common.exception.ErrorCodeAndMessage;
import dev.donggi.core.api.common.exception.GolrabaException;

public class InvalidTokenException extends GolrabaException {

    public InvalidTokenException() {
        super(ErrorCodeAndMessage.INVALID_TOKEN);
    }
}
