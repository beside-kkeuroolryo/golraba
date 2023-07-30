package dev.donggi.core.api.web.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginCommand {

    private String memberId;
    private String password;

    public LoginCommand(String memberId, String password) {
        this.memberId = memberId;
        this.password = password;
    }
}
