package donggi.dev.kkeuroolryo.core.question.domain.exception;

import donggi.dev.kkeuroolryo.common.exception.ErrorCodeAndMessage;
import donggi.dev.kkeuroolryo.common.exception.GolrabaException;

public class QuestionInvalidChoiceException extends GolrabaException {

    public QuestionInvalidChoiceException() {
        super(ErrorCodeAndMessage.QUESTION_INVALID_CHOICE);
    }
}
