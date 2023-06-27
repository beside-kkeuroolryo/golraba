package donggi.dev.kkeuroolryo.common.exception;

import lombok.Getter;

@Getter
public class GolrabaException extends RuntimeException {

    private final String code;

    public GolrabaException(ErrorCodeAndMessage errorCodeAndMessage) {
        super(errorCodeAndMessage.getMessage());
        this.code = errorCodeAndMessage.getCode();
    }

    public GolrabaException(ErrorCodeAndMessage errorCodeAndMessage, Throwable cause) {
        super(cause);
        this.code = errorCodeAndMessage.getCode();
    }
}
