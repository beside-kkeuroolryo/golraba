package donggi.dev.kkeuroolryo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("test DB 연결 테스트")
class KkeuroolryoApplicationTest {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Test
    void contextLoads() {
        String expected = "jdbc:tc:mysql:8://testDB";

        assertThat(url).isEqualTo(expected);
    }

    @Test
    void driver_connection() {
        String expected = "org.testcontainers.jdbc.ContainerDatabaseDriver";

        assertThat(driverClassName).isEqualTo(expected);
    }
  
}
