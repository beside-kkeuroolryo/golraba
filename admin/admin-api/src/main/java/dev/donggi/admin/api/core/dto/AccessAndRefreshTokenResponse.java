package dev.donggi.admin.api.core.dto;

import lombok.Getter;

@Getter
public class AccessAndRefreshTokenResponse {

    private String accessToken;
    private String refreshToken;

    public AccessAndRefreshTokenResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
