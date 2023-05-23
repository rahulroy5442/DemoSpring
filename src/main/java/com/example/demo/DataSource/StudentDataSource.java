package com.example.demo.DataSource;

import com.example.demo.hibernate.child.Student;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "memberEntityManagerFactory",
        transactionManagerRef = "memberTransactionManager",
        basePackages ="com.example.demo.DataSource"
)
public class StudentDataSource {

    @Primary
    @Bean(name = "customerDataSource")
    @ConfigurationProperties("spring.datasource.stutrac")
    public DataSourceProperties memberDataSourceProperties() {
        return new DataSourceProperties();
    }


    @Bean
    @Primary
    public DataSource memberDataSource() {
        return memberDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }



    @Primary
    @Bean(name = "memberEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean memberEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(memberDataSource())
                .packages(Student.class)
                .build();
    }


    @Primary
    @Bean
    public PlatformTransactionManager memberTransactionManager(
            final @Qualifier("memberEntityManagerFactory") LocalContainerEntityManagerFactoryBean memberEntityManagerFactory) {
        return new JpaTransactionManager(memberEntityManagerFactory.getObject());
    }
}