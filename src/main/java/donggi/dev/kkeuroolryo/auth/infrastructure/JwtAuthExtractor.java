package donggi.dev.kkeuroolryo.auth.infrastructure;

import donggi.dev.kkeuroolryo.auth.application.AuthExtractor;
import donggi.dev.kkeuroolryo.auth.application.dto.AuthPayload;
import donggi.dev.kkeuroolryo.common.exception.ErrorCodeAndMessage;
import donggi.dev.kkeuroolryo.common.exception.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;

public class JwtAuthExtractor implements AuthExtractor {

    private final JwtParser parser;

    public JwtAuthExtractor(final String secretKey) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.parser = Jwts.parserBuilder()
            .setSigningKey(key)
            .build();
    }

    @Override
    public AuthPayload extract(final String token) {
        final Claims claims = getClaims(token);
        return new AuthPayload(Long.parseLong(claims.getSubject()));
    }

    private Claims getClaims(final String token) {
        try {
            return parser.parseClaimsJws(token)
                .getBody();
        } catch (ExpiredJwtException e) {
            throw new UnauthorizedException(ErrorCodeAndMessage.EXPIRED_TOKEN);
        } catch (JwtException e) {
            throw new UnauthorizedException(ErrorCodeAndMessage.INVALID_TOKEN);
        }
    }
}
