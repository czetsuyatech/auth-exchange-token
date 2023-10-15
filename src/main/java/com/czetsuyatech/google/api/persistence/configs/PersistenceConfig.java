package com.czetsuyatech.google.api.persistence.configs;

import com.czetsuyatech.google.api.persistence.repositories.RepositoriesConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(
    basePackageClasses = {
        RepositoriesConfig.class
    }
)
@EnableTransactionManagement
public class PersistenceConfig {

}
