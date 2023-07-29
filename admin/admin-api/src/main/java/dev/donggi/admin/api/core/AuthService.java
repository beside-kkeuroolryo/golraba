package dev.donggi.admin.api.core;

import dev.donggi.admin.api.core.dto.AccessAndRefreshTokenResponse;
import dev.donggi.admin.api.core.dto.AuthToken;
import dev.donggi.admin.api.core.dto.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthTokenCreator authTokenCreator;

    @Transactional
    public AccessAndRefreshTokenResponse generateAccessAndRefreshToken(MemberResponse memberResponse) {
        String memberId = memberResponse.getMemberId();

        AuthToken authToken = authTokenCreator.createAuthToken(memberId);
        return new AccessAndRefreshTokenResponse(authToken.getAccessToken(), authToken.getRefreshToken());
    }
}
