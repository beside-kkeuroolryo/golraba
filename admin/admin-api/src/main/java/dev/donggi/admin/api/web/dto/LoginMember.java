package dev.donggi.admin.api.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginMember {

    private String id;

    public LoginMember(String id) {
        this.id = id;
    }
}
