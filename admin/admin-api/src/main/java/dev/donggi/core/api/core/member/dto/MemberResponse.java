package dev.donggi.core.api.core.member.dto;

import dev.donggi.core.api.core.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponse {

    private String memberId;

    public MemberResponse(String memberId) {
        this.memberId = memberId;
    }

    public MemberResponse(final Member member) {
        this(member.getMemberId());
    }
}
