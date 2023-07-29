package dev.donggi.admin.api.web;

import dev.donggi.admin.api.core.AuthService;
import dev.donggi.admin.api.core.MemberService;
import dev.donggi.admin.api.core.dto.AccessAndRefreshTokenResponse;
import dev.donggi.admin.api.core.dto.LoginResponse;
import dev.donggi.admin.api.core.dto.MemberResponse;
import dev.donggi.admin.api.web.dto.LoginCommand;
import donggi.dev.core.api.common.response.ApiResponse;
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
        return ApiResponse.success(new LoginResponse(memberResponse, tokenResponse));
    }
}
