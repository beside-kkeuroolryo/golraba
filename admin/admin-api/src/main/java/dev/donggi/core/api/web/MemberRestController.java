package dev.donggi.core.api.web;

import dev.donggi.core.api.core.auth.AuthService;
import dev.donggi.core.api.core.MemberService;
import dev.donggi.core.api.core.dto.AccessAndRefreshTokenResponse;
import dev.donggi.core.api.core.dto.LoginResponse;
import dev.donggi.core.api.core.dto.MemberResponse;
import dev.donggi.core.api.web.dto.LoginCommand;
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
