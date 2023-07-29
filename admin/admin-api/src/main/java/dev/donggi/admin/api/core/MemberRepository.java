package dev.donggi.admin.api.core;

import dev.donggi.admin.api.core.domain.Member;
import dev.donggi.admin.api.core.exception.MemberNotFoundException;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemberId(String memberId);

    default Member getByMemberId(String memberId) {
        return findByMemberId(memberId)
            .orElseThrow(MemberNotFoundException::new);
    }
}
