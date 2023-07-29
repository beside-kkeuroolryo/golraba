package dev.donggi.core.api.core.question.domain.exception;

import dev.donggi.core.api.common.exception.ErrorCodeAndMessage;
import dev.donggi.core.api.common.exception.GolrabaException;

public class QuestionNotFoundException extends GolrabaException {

    public QuestionNotFoundException() {
        super(ErrorCodeAndMessage.QUESTION_NOT_FOUND);
    }
}
