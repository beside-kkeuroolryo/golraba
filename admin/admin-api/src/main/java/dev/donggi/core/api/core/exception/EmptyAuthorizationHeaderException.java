package dev.donggi.core.api.core.exception;

import dev.donggi.core.api.common.exception.ErrorCodeAndMessage;
import dev.donggi.core.api.common.exception.GolrabaException;

public class EmptyAuthorizationHeaderException extends GolrabaException {

    public EmptyAuthorizationHeaderException() {
        super(ErrorCodeAndMessage.EMPTY_AUTHORIZATION_HEADER);
    }
}
