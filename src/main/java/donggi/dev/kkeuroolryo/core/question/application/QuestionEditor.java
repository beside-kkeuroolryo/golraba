package donggi.dev.kkeuroolryo.core.question.application;

import donggi.dev.kkeuroolryo.core.question.application.dto.QuestionDto;
import donggi.dev.kkeuroolryo.web.question.dto.QuestionRegisterCommand;

public interface QuestionEditor {

    /**
     * 사용자가 등록한 질문을 저장소에 저장합니다.
     *
     * @param questionRegisterCommand 질문 요청 객체
     * @return questionDto 등록된 question이 포함된 객체
     */
    QuestionDto save(QuestionRegisterCommand questionRegisterCommand);
}
