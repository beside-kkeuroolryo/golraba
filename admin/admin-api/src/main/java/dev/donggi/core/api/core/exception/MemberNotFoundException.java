package dev.donggi.core.api.core.exception;

import dev.donggi.core.api.common.exception.ErrorCodeAndMessage;
import dev.donggi.core.api.common.exception.GolrabaException;

public class MemberNotFoundException extends GolrabaException {

    public MemberNotFoundException() {
        super(ErrorCodeAndMessage.MEMBER_NOT_FOUND);
    }
}
