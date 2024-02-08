package donggi.dev.kkeuroolryo.url.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "url")
@TableGenerator(
    name = "urlIdGenerator",
    table = "tb_mgn_id",
    pkColumnName = "table_name",
    pkColumnValue = "myTable",
    valueColumnName = "table_max_id",
    initialValue = 1000000000,
    allocationSize = 1
)
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "urlIdGenerator")
    private Long id;

    private String originalData;

    public Url(String originalData) {
        this.originalData = originalData;
    }
}
