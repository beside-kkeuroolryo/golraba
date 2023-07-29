package dev.donggi.core.api.core.auth;

import dev.donggi.core.api.core.dto.AccessAndRefreshTokenResponse;
import dev.donggi.core.api.core.dto.AuthToken;
import dev.donggi.core.api.core.dto.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthTokenCreator tokenCreator;

    @Transactional
    public AccessAndRefreshTokenResponse generateAccessAndRefreshToken(MemberResponse memberResponse) {
        String memberId = memberResponse.getMemberId();

        AuthToken authToken = tokenCreator.createAuthToken(memberId);
        return new AccessAndRefreshTokenResponse(authToken.getAccessToken(), authToken.getRefreshToken());
    }

    public String extractMemberId(String accessToken) {
        String memberId = tokenCreator.extractPayload(accessToken);
        return memberId;
    }
}
