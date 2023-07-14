package donggi.dev.core.api.core.question.application;

import donggi.dev.core.api.core.question.application.dto.QuestionDto;
import donggi.dev.core.api.web.question.dto.QuestionRegisterCommand;
import donggi.dev.core.api.web.question.dto.QuestionResultCommand;

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
