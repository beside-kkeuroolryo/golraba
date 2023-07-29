package dev.donggi.admin.api.core.exception;

import donggi.dev.core.api.common.exception.ErrorCodeAndMessage;
import donggi.dev.core.api.common.exception.GolrabaException;

public class MemberInvalidPasswordException extends GolrabaException {

    public MemberInvalidPasswordException() {
        super(ErrorCodeAndMessage.MEMBER_INVALID_PASSWORD);
    }
}
