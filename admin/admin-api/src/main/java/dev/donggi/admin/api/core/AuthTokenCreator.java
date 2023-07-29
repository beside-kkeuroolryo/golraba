package dev.donggi.admin.api.core;

import dev.donggi.admin.api.core.dto.AuthToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthTokenCreator {

    private final TokenProvider tokenProvider;

    public AuthToken createAuthToken(String memberId) {
        String accessToken = tokenProvider.createAccessToken(memberId);
        String refreshToken = tokenProvider.createRefreshToken(memberId);
        return new AuthToken(accessToken, refreshToken);
    }
}
