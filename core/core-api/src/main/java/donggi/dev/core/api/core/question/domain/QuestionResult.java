package donggi.dev.core.api.core.question.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "question_result")
public class QuestionResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(name = "choice_a_result", columnDefinition = "integer default 0", nullable = false)
    private int choiceAResult;

    @Column(name = "choice_b_result", columnDefinition = "integer default 0", nullable = false)
    private int choiceBResult;

    public QuestionResult(Question question) {
        this.question = question;
    }

    public void incrementChoiceA() {
        choiceAResult++;
    }

    public void incrementChoiceB() {
        choiceBResult++;
    }
}
