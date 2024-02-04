package donggi.dev.kkeuroolryo.user.domain.exception;

import donggi.dev.kkeuroolryo.common.exception.ErrorCodeAndMessage;
import donggi.dev.kkeuroolryo.common.exception.GolrabaException;

public class InvalidPasswordException extends GolrabaException {

    public InvalidPasswordException() {
        super(ErrorCodeAndMessage.USER_INVALID_PASSWORD);
    }
}
