package donggi.dev.kkeuroolryo.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCodeAndMessage {
    // common
    NO_OFFSET_INVALID_PAGE("P401", "유효하지 않은 페이지 값입니다."),

    // question
    QUESTION_INVALID_CATEGORY("Q401", "유효하지 않은 카테고리입니다."),
    QUESTION_INVALID_CONTENT("Q402", "유효하지 않은 질문 본문입니다."),
    QUESTION_INVALID_CHOICE("Q403", "유효하지 않은 선택값입니다."),
    QUESTION_NOT_FOUND("Q404", "존재하지 않는 질문입니다."),

    // comment
    COMMENT_INVALID_CONTENT("C401", "유효하지 않은 댓글 본문입니다."),
    COMMENT_INVALID_PASSWORD("C402", "유효하지 않은 댓글 비밀번호입니다."),
    COMMENT_INVALID_USERNAME("C403", "유효하지 않은 사용자 이름입니다."),
    COMMENT_NOT_FOUND("C404", "존재하지 않은 댓글입니다."),
    COMMENT_UNAUTHORIZED("C405", "올바르지 않은 댓글 비밀번호입니다.")
    ;

    private final String code;
    private final String message;

    ErrorCodeAndMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
