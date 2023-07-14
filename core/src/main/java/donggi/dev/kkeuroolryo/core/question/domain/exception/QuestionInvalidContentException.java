package donggi.dev.kkeuroolryo.core.question.domain.exception;

import donggi.dev.kkeuroolryo.common.exception.ErrorCodeAndMessage;
import donggi.dev.kkeuroolryo.common.exception.GolrabaException;

public class QuestionInvalidContentException extends GolrabaException {

    public QuestionInvalidContentException() {
        super(ErrorCodeAndMessage.QUESTION_INVALID_CONTENT);
    }
}
