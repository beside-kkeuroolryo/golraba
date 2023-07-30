package dev.donggi.core.api.core.member;

import dev.donggi.core.api.core.member.domain.Member;
import dev.donggi.core.api.core.member.dto.MemberResponse;
import dev.donggi.core.api.web.member.dto.LoginCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public MemberResponse findByMemberId(LoginCommand loginCommand) {
        Member member = memberRepository.getByMemberId(loginCommand.getMemberId());

        member.checkPassword(loginCommand.getPassword());

        return new MemberResponse(member);
    }
}
