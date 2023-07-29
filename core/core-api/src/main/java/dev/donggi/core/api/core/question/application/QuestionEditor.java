package dev.donggi.core.api.core.question.application;

import dev.donggi.core.api.web.question.dto.QuestionRegisterCommand;
import dev.donggi.core.api.web.question.dto.QuestionResultCommand;
import dev.donggi.core.api.core.question.application.dto.QuestionDto;

public interface QuestionEditor {

    /**
     * 사용자가 등록한 질문을 저장소에 저장합니다.
     *
     * @param questionRegisterCommand 질문 요청 객체
     * @return questionDto 등록된 question이 포함된 객체
     */
    QuestionDto save(QuestionRegisterCommand questionRegisterCommand);

    /**
     * 사용자의 질문 선택 결과를 저장소에 저장합니다.
     *
     * @param resultCommand 선택 결과 객체
     */
    void result(QuestionResultCommand resultCommand);
}
