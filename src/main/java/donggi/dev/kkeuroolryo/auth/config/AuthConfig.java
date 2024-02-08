package donggi.dev.kkeuroolryo.auth.config;

import donggi.dev.kkeuroolryo.auth.application.AuthExtractor;
import donggi.dev.kkeuroolryo.auth.infrastructure.JwtAuthExtractor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthConfig {

    private final String secretKey;

    public AuthConfig(@Value("${security.jwt.secret-key}") final String secretKey) {
        this.secretKey = secretKey;
    }

    @Bean
    public AuthExtractor authExtractor() {
        return new JwtAuthExtractor(secretKey);
    }
}
