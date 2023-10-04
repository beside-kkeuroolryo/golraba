package donggi.dev.kkeuroolryo.core.question.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToOne;
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

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Category category;

    @Embedded
    private QuestionContent content;

    private boolean active = true;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "choice", column = @Column(name = "choice_a"))
    })
    private QuestionChoice choiceA;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "choice", column = @Column(name = "choice_b"))
    })
    private QuestionChoice choiceB;

    @OneToOne(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private QuestionResult questionResult;

    public Question(String content, String choiceA, String choiceB, Category category) {
        this.content = new QuestionContent(content);
        this.choiceA = new QuestionChoice(choiceA);
        this.choiceB = new QuestionChoice(choiceB);

        if (category.equals(Category.USERMADE)) {
            active = false;
        }
        this.category = category;
    }
}
