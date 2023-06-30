package donggi.dev.kkeuroolryo.web.question.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuestionResultCommand {

    private List<ChoiceResult> results = new ArrayList<>();

    public QuestionResultCommand(List<ChoiceResult> results) {
        this.results = results;
    }

    @Getter
    public static class ChoiceResult {
        private Long questionId;
        private String choice;

        public ChoiceResult(Long questionId, String choice) {
            this.questionId = questionId;
            this.choice = choice;
        }
    }
}
