package dev.donggi.admin.api.core.auth;

import dev.donggi.admin.api.core.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider implements TokenProvider {

    private final SecretKey key;
    private final Long accessTokenExpiredTime;
    private final Long refreshTokenExpiredTime;

    public JwtTokenProvider(@Value("${spring.security.jwt.token.secret-key}") String secretKey,
                            @Value("${spring.security.jwt.token.access.expired-time}") long accessTokenExpiredTime,
                            @Value("${spring.security.jwt.token.refresh.expired-time}") long refreshTokenExpiredTime) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.accessTokenExpiredTime = accessTokenExpiredTime;
        this.refreshTokenExpiredTime = refreshTokenExpiredTime;
    }

    @Override
    public String createAccessToken(String payload) {
        return createToken(payload, accessTokenExpiredTime);
    }

    @Override
    public String createRefreshToken(String payload) {
        return createToken(payload, refreshTokenExpiredTime);
    }

    private String createToken(String payload, Long expiredTime) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expiredTime);

        return Jwts.builder()
            .setSubject(payload)
            .setIssuedAt(now)
            .setExpiration(expiration)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }

    @Override
    public String getPayload(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    @Override
    public void validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);

            claims.getBody()
                .getExpiration()
                .before(new Date());
        } catch (final JwtException | IllegalArgumentException e) {
            throw new InvalidTokenException();
        }
    }
}
