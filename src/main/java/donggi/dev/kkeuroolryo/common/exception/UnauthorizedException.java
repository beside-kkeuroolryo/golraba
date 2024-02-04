package donggi.dev.kkeuroolryo.common.exception;

public class UnauthorizedException extends GolrabaException {

    public UnauthorizedException(final ErrorCodeAndMessage errorCodeAndMessage) {
        super(errorCodeAndMessage);
    }
}
