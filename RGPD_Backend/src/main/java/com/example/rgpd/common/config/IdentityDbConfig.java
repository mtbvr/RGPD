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
import org.springframework.jdbc.datasource.init.*;
import org.springframework.orm.jpa.*;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.example.rgpd.common.repository.identity",
        entityManagerFactoryRef = "identityEntityManager",
        transactionManagerRef = "identityTransactionManager"
)
public class IdentityDbConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.identity")
    public DataSourceProperties identityDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource identityDataSource() {
        return identityDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean identityEntityManager(
            EntityManagerFactoryBuilder builder) {

        return builder
                .dataSource(identityDataSource())
                .packages("com.example.rgpd.common.entity.identity")
                .persistenceUnit("identity")
                .properties(jpaProps())
                .build();
    }

    @Bean
    public PlatformTransactionManager identityTransactionManager(
            @Qualifier("identityEntityManager") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean
    public DataSourceInitializer identityDataSourceInitializer() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("data-identity.sql"));

        DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(identityDataSource());
        initializer.setDatabasePopulator(populator);

        return initializer;
    }

    private Map<String, Object> jpaProps() {
        return Map.of(
                "hibernate.hbm2ddl.auto", "update",
                "hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect"
        );
    }
}