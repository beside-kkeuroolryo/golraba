package dev.donggi.core.api.core.exception;

import dev.donggi.core.api.common.exception.ErrorCodeAndMessage;
import dev.donggi.core.api.common.exception.GolrabaException;

public class MemberInvalidPasswordException extends GolrabaException {

    public MemberInvalidPasswordException() {
        super(ErrorCodeAndMessage.MEMBER_INVALID_PASSWORD);
    }
}
