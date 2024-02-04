package donggi.dev.kkeuroolryo.support;

import donggi.dev.kkeuroolryo.common.config.WebConfig;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@TestConfiguration
public class TestWebConfig implements WebMvcConfigurer {

    @Bean
    WebConfig getWebConfig() {
        return new WebConfig();
    }
}
