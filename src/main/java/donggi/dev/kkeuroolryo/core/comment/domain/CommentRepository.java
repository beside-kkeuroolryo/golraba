package donggi.dev.kkeuroolryo.core.comment.domain;

public interface CommentRepository {

    /**
     * comment 를 저장소에 저장합니다.
     * @param comment 저장할 comment
     * @return 저장된 comment
     */
    Comment save(Comment comment);
}
