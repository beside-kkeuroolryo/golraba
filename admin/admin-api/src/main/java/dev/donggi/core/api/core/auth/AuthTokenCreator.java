package dev.donggi.core.api.core.auth;

import dev.donggi.core.api.core.dto.AuthToken;
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

    public String extractPayload(String accessToken) {
        tokenProvider.validateToken(accessToken);
        return tokenProvider.getPayload(accessToken);
    }
}
