package dev.donggi.admin.api.core.exception;

import donggi.dev.core.api.common.exception.ErrorCodeAndMessage;
import donggi.dev.core.api.common.exception.GolrabaException;

public class EmptyAuthorizationHeaderException extends GolrabaException {

    public EmptyAuthorizationHeaderException() {
        super(ErrorCodeAndMessage.EMPTY_AUTHORIZATION_HEADER);
    }
}
