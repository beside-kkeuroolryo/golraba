package dev.donggi.core.api.core.member;

import dev.donggi.core.api.core.member.domain.Member;
import dev.donggi.core.api.core.member.exception.MemberNotFoundException;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemberId(String memberId);

    default Member getByMemberId(String memberId) {
        return findByMemberId(memberId)
            .orElseThrow(MemberNotFoundException::new);
    }
}
