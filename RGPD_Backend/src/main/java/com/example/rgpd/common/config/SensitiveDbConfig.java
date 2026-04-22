package com.example.rgpd.common.config;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.*;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.example.rgpd.common.repository.sensitive",
        entityManagerFactoryRef = "sensitiveEntityManager",
        transactionManagerRef = "sensitiveTransactionManager"
)
public class SensitiveDbConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.sensitive")
    public DataSourceProperties sensitiveDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource sensitiveDataSource() {
        return sensitiveDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean sensitiveEntityManager(
            EntityManagerFactoryBuilder builder) {

        return builder
                .dataSource(sensitiveDataSource())
                .packages("com.example.rgpd.common.entity.sensitive")
                .persistenceUnit("sensitive")
                .properties(jpaProps())
                .build();
    }

    @Bean
    public PlatformTransactionManager sensitiveTransactionManager(
            @Qualifier("sensitiveEntityManager") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    private java.util.Map<String, Object> jpaProps() {
        return Map.of(
                "hibernate.hbm2ddl.auto", "update",
                "hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect"
        );
    }
}