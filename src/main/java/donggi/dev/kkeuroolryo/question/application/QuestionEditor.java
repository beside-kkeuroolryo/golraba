package donggi.dev.kkeuroolryo.question.application;

import donggi.dev.kkeuroolryo.question.application.dto.QuestionDto;
import donggi.dev.kkeuroolryo.question.dto.QuestionRegisterDto;
import donggi.dev.kkeuroolryo.question.dto.QuestionResultCommand;

public interface QuestionEditor {

    /**
     * 사용자가 등록한 질문을 저장소에 저장합니다.
     *
     * @param questionRegisterDto 질문 요청 객체
     * @return questionDto 등록된 question이 포함된 객체
     */
    QuestionDto save(QuestionRegisterDto questionRegisterDto);

    /**
     * 사용자의 질문 선택 결과를 저장소에 저장합니다.
     *
     * @param resultCommand 선택 결과 객체
     */
    void result(QuestionResultCommand resultCommand);

    /**
     * 질문의 활성화 상태를 변경합니다.
     *
     * @param questionId 변경할 질문 id
     * @param active    변경할 active 상태
     */
    void changeActive(Long questionId, boolean active);

    /**
     * 사용자가 등록한 질문을 수정합니다.
     *
     * @param questionRegisterDto 질문 수정 객체
     */
    void modify(Long questionId, QuestionRegisterDto questionRegisterDto);
}
