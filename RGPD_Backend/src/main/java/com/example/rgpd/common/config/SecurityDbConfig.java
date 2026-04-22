package com.example.rgpd.common.config;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.*;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.example.rgpd.common.repository.security",
        entityManagerFactoryRef = "securityEntityManager",
        transactionManagerRef = "securityTransactionManager"
)
public class SecurityDbConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.security")
    public DataSourceProperties securityDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource securityDataSource() {
        return securityDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean securityEntityManager(
            EntityManagerFactoryBuilder builder) {

        return builder
                .dataSource(securityDataSource())
                .packages("com.example.rgpd.common.entity.security")
                .persistenceUnit("security")
                .properties(jpaProps())
                .build();
    }

    @Bean
    public PlatformTransactionManager securityTransactionManager(
            @Qualifier("securityEntityManager") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean
    public DataSourceInitializer securityDataSourceInitializer() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("data-security.sql"));

        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(securityDataSource());
        initializer.setDatabasePopulator(populator);

        return initializer;
    }

    private java.util.Map<String, Object> jpaProps() {
        return Map.of(
                "hibernate.hbm2ddl.auto", "update",
                "hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect"
        );
    }
}