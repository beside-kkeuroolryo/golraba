package donggi.dev.kkeuroolryo.core.comment.domain.exception;

import donggi.dev.kkeuroolryo.common.exception.ErrorCodeAndMessage;
import donggi.dev.kkeuroolryo.common.exception.GolrabaException;

public class NoOffsetPageInvalidException extends GolrabaException {

    public NoOffsetPageInvalidException() {
        super(ErrorCodeAndMessage.NO_OFFSET_INVALID_PAGE);
    }

    public NoOffsetPageInvalidException(Throwable cause) {
        super(ErrorCodeAndMessage.NO_OFFSET_INVALID_PAGE, cause);
    }
}
