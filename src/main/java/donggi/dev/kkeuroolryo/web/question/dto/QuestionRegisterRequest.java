package donggi.dev.kkeuroolryo.web.question.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuestionRegisterRequest {

    private String content;
    private String choiceA;
    private String choiceB;

    public QuestionRegisterRequest(String content, String choiceA, String choiceB) {
        this.content = content;
        this.choiceA = choiceA;
        this.choiceB = choiceB;
    }
}
