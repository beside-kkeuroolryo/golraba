package donggi.dev.kkeuroolryo.user.domain.exception;

import donggi.dev.kkeuroolryo.common.exception.ErrorCodeAndMessage;
import donggi.dev.kkeuroolryo.common.exception.GolrabaException;

public class UserNotFoundException extends GolrabaException {

    public UserNotFoundException() {
        super(ErrorCodeAndMessage.USER_NOT_FOUND);
    }
}
