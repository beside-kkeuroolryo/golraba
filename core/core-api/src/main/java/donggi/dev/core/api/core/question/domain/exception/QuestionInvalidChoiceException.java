package donggi.dev.core.api.core.question.domain.exception;

import donggi.dev.core.api.common.exception.ErrorCodeAndMessage;
import donggi.dev.core.api.common.exception.GolrabaException;

public class QuestionInvalidChoiceException extends GolrabaException {

    public QuestionInvalidChoiceException() {
        super(ErrorCodeAndMessage.QUESTION_INVALID_CHOICE);
    }
}
