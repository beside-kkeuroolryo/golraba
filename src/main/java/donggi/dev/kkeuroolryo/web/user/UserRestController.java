package donggi.dev.kkeuroolryo.web.user;

import donggi.dev.kkeuroolryo.common.response.ApiResponse;
import donggi.dev.kkeuroolryo.core.user.application.UserService;
import donggi.dev.kkeuroolryo.core.user.application.dto.LoginTokens;
import donggi.dev.kkeuroolryo.web.user.dto.LoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserRestController {

    private final UserService userService;

    @PostMapping("/login")
    public ApiResponse<LoginTokens> login(@RequestBody final LoginRequestDto loginRequestDto) {
        final LoginTokens loginTokens = userService.login(loginRequestDto);
        return ApiResponse.success(loginTokens);
    }
}
