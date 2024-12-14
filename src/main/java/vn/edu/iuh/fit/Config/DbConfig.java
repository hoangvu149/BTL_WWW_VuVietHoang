package vn.edu.iuh.fit.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "vn.edu.iuh.fit.Repositories")
@EnableTransactionManagement
public class DbConfig {
}

