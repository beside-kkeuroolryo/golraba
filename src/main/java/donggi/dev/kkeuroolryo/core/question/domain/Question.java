package donggi.dev.kkeuroolryo.core.question.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "question", indexes = @Index(name = "I_category", columnList = "category"))
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private QuestionCategory category;

    @Embedded
    private QuestionContent content;

    @Column(nullable = false)
    private String choiceA;

    @Column(nullable = false)
    private String choiceB;

    public Question(String category, String content, String choiceA, String choiceB) {
        this.category = new QuestionCategory(category);
        this.content = new QuestionContent(content);
        this.choiceA = choiceA;
        this.choiceB = choiceB;
    }
}
