package dev.donggi.core.api.web.member;

import dev.donggi.core.api.core.member.auth.AuthService;
import dev.donggi.core.api.core.member.MemberService;
import dev.donggi.core.api.core.member.dto.AccessAndRefreshTokenResponse;
import dev.donggi.core.api.core.member.dto.LoginResponse;
import dev.donggi.core.api.core.member.dto.MemberResponse;
import dev.donggi.core.api.web.member.dto.LoginCommand;
import dev.donggi.core.api.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class MemberRestController {

    private final MemberService memberService;
    private final AuthService authService;

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody LoginCommand loginCommand) {
        MemberResponse memberResponse = memberService.findByMemberId(loginCommand);
        AccessAndRefreshTokenResponse tokenResponse = authService.generateAccessAndRefreshToken(memberResponse);
        System.out.println(tokenResponse.getAccessToken());
        return ApiResponse.success(new LoginResponse(memberResponse, tokenResponse));
    }
}
