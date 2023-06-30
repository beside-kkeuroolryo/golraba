package donggi.dev.kkeuroolryo.web.question.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuestionResultCommand {

    private List<QuestionResult> results = new ArrayList<>();

    public QuestionResultCommand(List<QuestionResult> results) {
        this.results = results;
    }

    @Getter
    public static class QuestionResult {
        private Long id;
        private String choice;

        public QuestionResult(Long id, String choice) {
            this.id = id;
            this.choice = choice;
        }
    }
}
