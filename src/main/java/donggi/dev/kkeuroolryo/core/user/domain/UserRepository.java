package donggi.dev.kkeuroolryo.core.user.domain;

public interface UserRepository {

    /**
     * 저장소에서 유저를 조회합니다.
     * @param id
     * @return 조회한 user
     */
    User getByLoginId(final String loginId);

    /**
     * 저장소에 유저를 저장합니다.
     * @param user 저장할 유저 객체
     * @return 저장한 유저 객체
     */
    User save(final User user);

    /**
     * 저장소에 유저 데이터를 모두 삭제합니다.
     */
    void deleteAllInBatch();

    /**
     * 저장소에서 loginId로 User 존재 여부를 확인합니다.
     * @param loginId
     * @return 요청한 loginId가 존재하면 true, 존재하지 않으면 false 반환
     */
    boolean existsByLoginId(String loginId);
}
