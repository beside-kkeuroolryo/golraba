package donggi.dev.kkeuroolryo.core.question.application.dto;

import donggi.dev.kkeuroolryo.core.question.domain.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuestionDto {

    private Long id;
    private String content;
    private String choiceA;
    private String choiceB;

    public static QuestionDto ofEntity(Question question) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.id = question.getId();
        questionDto.content = question.getContent().getContent();
        questionDto.choiceA = question.getChoiceA();
        questionDto.choiceB = question.getChoiceB();
        return questionDto;
    }

    // todo 임시로 생성 질문 요청 로직 개발 시 다시 삭제
    public QuestionDto(Long id, String content, String choiceA, String choiceB) {
        this.id = id;
        this.content = content;
        this.choiceA = choiceA;
        this.choiceB = choiceB;
    }
}
