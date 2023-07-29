package dev.donggi.admin.api.core.dto;

import dev.donggi.admin.api.core.domain.Member;
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
