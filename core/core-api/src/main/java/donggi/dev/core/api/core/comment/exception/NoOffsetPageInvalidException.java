package donggi.dev.core.api.core.comment.exception;

import donggi.dev.core.api.common.exception.ErrorCodeAndMessage;
import donggi.dev.core.api.common.exception.GolrabaException;

public class NoOffsetPageInvalidException extends GolrabaException {

    public NoOffsetPageInvalidException() {
        super(ErrorCodeAndMessage.NO_OFFSET_INVALID_PAGE);
    }

    public NoOffsetPageInvalidException(Throwable cause) {
        super(ErrorCodeAndMessage.NO_OFFSET_INVALID_PAGE, cause);
    }
}
