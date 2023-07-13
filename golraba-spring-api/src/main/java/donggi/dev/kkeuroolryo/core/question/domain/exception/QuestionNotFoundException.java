package donggi.dev.kkeuroolryo.core.question.domain.exception;

import donggi.dev.kkeuroolryo.common.exception.ErrorCodeAndMessage;
import donggi.dev.kkeuroolryo.common.exception.GolrabaException;

public class QuestionNotFoundException extends GolrabaException {

    public QuestionNotFoundException() {
        super(ErrorCodeAndMessage.QUESTION_NOT_FOUND);
    }
}
