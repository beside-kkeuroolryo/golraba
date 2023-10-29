package donggi.dev.kkeuroolryo.core.user.domain.exception;

import donggi.dev.kkeuroolryo.common.exception.ErrorCodeAndMessage;
import donggi.dev.kkeuroolryo.common.exception.GolrabaException;

public class DuplicatedLoginIdException extends GolrabaException {

    public DuplicatedLoginIdException() {
        super(ErrorCodeAndMessage.DUPLICATED_LOGIN_ID);
    }
}
