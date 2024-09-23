package pl.chmielewski.Euro.Authentication.auth;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import javax.sql.DataSource;

public class DataSourceConfig {

    @Autowired
    private DataSource dataSource;

    @PreDestroy
    public void close() {
        if (dataSource instanceof HikariDataSource) {
            ((HikariDataSource) dataSource).close();
        }
    }
}
