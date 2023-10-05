package donggi.dev.kkeuroolryo.core.comment.domain;

import donggi.dev.kkeuroolryo.core.comment.domain.exception.CommentUnauthorizedException;
import donggi.dev.kkeuroolryo.web.comment.dto.CommentRegisterDto;
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

    public void checkPassword(String password) {
        if (!this.password.getPassword().equals(password)) {
            throw new CommentUnauthorizedException();
        }
    }

    public void modify(CommentRegisterDto request) {
        this.username = new CommentUsername(request.getUsername());
        this.password = new CommentPassword(request.getPassword());
        this.content = new CommentContent(request.getContent());
    }
}
