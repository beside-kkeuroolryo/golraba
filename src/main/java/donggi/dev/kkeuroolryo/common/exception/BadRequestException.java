package donggi.dev.kkeuroolryo.common.exception;

public class BadRequestException extends GolrabaException {

    public BadRequestException(final ErrorCodeAndMessage errorCodeAndMessage) {
        super(errorCodeAndMessage);
    }
}
