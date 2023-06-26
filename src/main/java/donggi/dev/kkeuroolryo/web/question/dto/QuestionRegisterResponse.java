package donggi.dev.kkeuroolryo.web.question.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuestionRegisterResponse {

    private Long id;
    private String content;
    private String choiceA;
    private String choiceB;

    public QuestionRegisterResponse(Long id, String content, String choiceA, String choiceB) {
        this.id = id;
        this.content = content;
        this.choiceA = choiceA;
        this.choiceB = choiceB;
    }
}
