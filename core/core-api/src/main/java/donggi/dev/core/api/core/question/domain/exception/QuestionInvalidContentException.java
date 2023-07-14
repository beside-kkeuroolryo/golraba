package donggi.dev.core.api.core.question.domain.exception;

import donggi.dev.core.api.common.exception.ErrorCodeAndMessage;
import donggi.dev.core.api.common.exception.GolrabaException;

public class QuestionInvalidContentException extends GolrabaException {

    public QuestionInvalidContentException() {
        super(ErrorCodeAndMessage.QUESTION_INVALID_CONTENT);
    }
}
