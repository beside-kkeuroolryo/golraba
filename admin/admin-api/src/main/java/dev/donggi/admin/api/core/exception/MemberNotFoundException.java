package dev.donggi.admin.api.core.exception;

import donggi.dev.core.api.common.exception.ErrorCodeAndMessage;
import donggi.dev.core.api.common.exception.GolrabaException;

public class MemberNotFoundException extends GolrabaException {

    public MemberNotFoundException() {
        super(ErrorCodeAndMessage.MEMBER_NOT_FOUND);
    }
}
