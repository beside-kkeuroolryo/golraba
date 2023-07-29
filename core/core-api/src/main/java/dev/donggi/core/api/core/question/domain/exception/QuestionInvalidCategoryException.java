package dev.donggi.core.api.core.question.domain.exception;

import dev.donggi.core.api.common.exception.ErrorCodeAndMessage;
import dev.donggi.core.api.common.exception.GolrabaException;

public class QuestionInvalidCategoryException extends GolrabaException {

    public QuestionInvalidCategoryException() {
        super(ErrorCodeAndMessage.QUESTION_INVALID_CATEGORY);
    }
}
