package dev.donggi.core.api.core.member.auth;

public interface TokenProvider {

    String createAccessToken(final String payload);

    String createRefreshToken(final String payload);

    String getPayload(final String token);

    void validateToken(final String token);
}
