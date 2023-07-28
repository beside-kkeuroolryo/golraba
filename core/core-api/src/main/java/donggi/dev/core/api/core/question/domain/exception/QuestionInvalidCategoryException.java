package donggi.dev.core.api.core.question.domain.exception;

import donggi.dev.core.api.common.exception.ErrorCodeAndMessage;
import donggi.dev.core.api.common.exception.GolrabaException;

public class QuestionInvalidCategoryException extends GolrabaException {

    public QuestionInvalidCategoryException() {
        super(ErrorCodeAndMessage.QUESTION_INVALID_CATEGORY);
    }
}
