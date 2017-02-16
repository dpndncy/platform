package com.dpndncy.db.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.PropertySource
import org.springframework.context.annotation.PropertySources
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement

import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

/**
 * Created by vaibhav on 06/02/17.
 */
@Configuration
@EnableJpaRepositories(basePackages = 'com.dpndncy.db')
@EnableTransactionManagement
@PropertySources(@PropertySource("file:/dpndncy.properties"))
@Import([RestConfig, AclConfig])
class DbConfig {

    @Value('${jdbc.url}')
    String jdbcUrl;

    @Value('${jdbc.username}')
    String jdbcUsername;

    @Value('${jdbc.password}')
    String jdbcPassword;

    @Bean(name = 'dbDataSource')
    public DataSource dataSource() {
        DataSource dataSource = new DriverManagerDataSource();
        dataSource.driverClassName = 'com.mysql.jdbc.Driver';
        dataSource.url = jdbcUrl;
        dataSource.username = jdbcUsername;
        dataSource.password = jdbcPassword;
        dataSource.setConnectionProperties(hibernateProperties());
        return dataSource;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.dpndncy.db");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean
    @Autowired
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.entityManagerFactory = entityManagerFactory();
        return txManager;
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource());
        return dataSourceTransactionManager;
    }

    Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.hbm2ddl.auto", "update");
                setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            }
        };
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfig() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
