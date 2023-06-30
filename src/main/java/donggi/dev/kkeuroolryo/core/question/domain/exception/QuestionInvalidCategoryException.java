package donggi.dev.kkeuroolryo.core.question.domain.exception;

import donggi.dev.kkeuroolryo.common.exception.ErrorCodeAndMessage;
import donggi.dev.kkeuroolryo.common.exception.GolrabaException;

public class QuestionInvalidCategoryException extends GolrabaException {

    public QuestionInvalidCategoryException() {
        super(ErrorCodeAndMessage.QUESTION_INVALID_CATEGORY);
    }
}
