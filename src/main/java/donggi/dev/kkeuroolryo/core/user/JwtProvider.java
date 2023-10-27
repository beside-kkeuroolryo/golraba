package donggi.dev.kkeuroolryo.core.user;

import donggi.dev.kkeuroolryo.core.user.application.dto.LoginTokens;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {

    private final SecretKey secretKey;
    private final Long accessExpirationTime;
    private final Long refreshExpirationTime;

    public JwtProvider(
        @Value("${security.jwt.secret-key}") final String secretKey,
        @Value("${security.jwt.access-expiration-time}") final Long accessExpirationTime,
        @Value("${security.jwt.refresh-expiration-time}") final Long refreshExpirationTime
    ) {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.accessExpirationTime = accessExpirationTime;
        this.refreshExpirationTime = refreshExpirationTime;
    }

    public LoginTokens generateLoginToken(final String subject) {
        String accessToken = createToken(subject, accessExpirationTime);
        String refreshToken = createToken(subject, refreshExpirationTime);
        return new LoginTokens(accessToken, refreshToken);
    }

    private String createToken(final String subject, final Long expirationMilliSeconds) {
        Date now = new Date();
        Date expirationAt = new Date(now.getTime() + expirationMilliSeconds);
        return Jwts.builder()
            .setSubject(subject)
            .setIssuedAt(now)
            .setExpiration(expirationAt)
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact();
    }
}
