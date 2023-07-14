package donggi.dev.core.api.core.question.domain.exception;

import donggi.dev.core.api.common.exception.ErrorCodeAndMessage;
import donggi.dev.core.api.common.exception.GolrabaException;

public class QuestionNotFoundException extends GolrabaException {

    public QuestionNotFoundException() {
        super(ErrorCodeAndMessage.QUESTION_NOT_FOUND);
    }
}
