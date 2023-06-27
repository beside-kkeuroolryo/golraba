package donggi.dev.kkeuroolryo.core.comment.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long questionId;

    @Embedded
    private CommentUsername username;

    @Embedded
    private CommentPassword password;

    @Embedded
    private CommentContent content;

    public Comment(Long questionId, String username, String password, String content) {
        this.questionId = questionId;
        this.username = new CommentUsername(username);
        this.password = new CommentPassword(password);
        this.content = new CommentContent(content);
    }
}
