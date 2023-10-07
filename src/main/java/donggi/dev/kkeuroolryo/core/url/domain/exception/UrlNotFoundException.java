package donggi.dev.kkeuroolryo.core.url.domain.exception;

import donggi.dev.kkeuroolryo.common.exception.ErrorCodeAndMessage;
import donggi.dev.kkeuroolryo.common.exception.GolrabaException;

public class UrlNotFoundException extends GolrabaException {

    public UrlNotFoundException() {
        super(ErrorCodeAndMessage.URL_NOT_FOUND);
    }
}
