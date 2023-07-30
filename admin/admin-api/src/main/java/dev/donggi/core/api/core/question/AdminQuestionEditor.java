package dev.donggi.core.api.core.question;

import dev.donggi.core.api.core.question.dto.AdminQuestionDto;
import dev.donggi.core.api.web.question.dto.AdminQuestionRegisterCommand;
import dev.donggi.core.api.web.question.dto.AdminQuestionUpdateCommand;

public interface AdminQuestionEditor {

    /**
     * 관리자가 질문을 저장소에 저장합니다.
     *
     * @param adminQuestionRegisterCommand 질문 요청 객체
     * @return questionDto 등록된 question이 포함된 객체
     */
    AdminQuestionDto save(AdminQuestionRegisterCommand adminQuestionRegisterCommand);

    /**
     * 관리자가 질문을 수정합니다.
     *
     * @param questionId 수정할 질문 id
     * @param adminQuestionUpdateCommand 수정할 질문 요청 객체
     * @return adminQuestionDto 수정된 question이 포함된 객체
     */
    void update(Long questionId, AdminQuestionUpdateCommand adminQuestionUpdateCommand);

    /**
     * 관리자가 질문을 삭제합니다.
     *
     * @param questionId 삭제할 질문 id
     */
    void delete(Long questionId);
}
