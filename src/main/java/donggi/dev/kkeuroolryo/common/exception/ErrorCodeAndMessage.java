package donggi.dev.kkeuroolryo.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCodeAndMessage {
    // question
    QUESTION_INVALID_CATEGORY("Q401", "유효하지 않은 카테고리입니다."),
    ;

    private final String code;
    private final String message;

    ErrorCodeAndMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
