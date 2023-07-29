package dev.donggi.core.api.core.dto;

import lombok.Getter;

@Getter
public class LoginResponse {

    private String memberId;
    private String accessToken;
    private String RefreshToken;

    public LoginResponse(String memberId, String accessToken, String refreshToken) {
        this.memberId = memberId;
        this.accessToken = accessToken;
        RefreshToken = refreshToken;
    }

    public LoginResponse(MemberResponse memberResponse, AccessAndRefreshTokenResponse tokenResponse) {
        this(memberResponse.getMemberId(), tokenResponse.getAccessToken(), tokenResponse.getRefreshToken());
    }
}
