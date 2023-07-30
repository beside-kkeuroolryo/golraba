package dev.donggi.core.api.core.question;

import dev.donggi.core.api.core.question.dto.AdminQuestionDto;
import dev.donggi.core.api.web.question.dto.AdminQuestionRegisterCommand;

public interface AdminQuestionEditor {

    /**
     * 관리자가 질문을 저장소에 저장합니다.
     *
     * @param adminQuestionRegisterCommand 질문 요청 객체
     * @return questionDto 등록된 question이 포함된 객체
     */
    AdminQuestionDto save(AdminQuestionRegisterCommand adminQuestionRegisterCommand);
}
