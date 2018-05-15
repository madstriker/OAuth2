package com.my.config;

import com.my.commons.YAMLConfig;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"com.my"})
@EnableTransactionManagement
public class HibernateConfig {

    @Autowired
    private YAMLConfig yamlConfig;

    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource dataSource=new DriverManagerDataSource();
        dataSource.setDriverClassName(yamlConfig.getDriver());
        dataSource.setUrl(yamlConfig.getUrl());
        dataSource.setUsername(yamlConfig.getUsername());
        dataSource.setPassword(yamlConfig.getPassword());
        return dataSource;
    }

    @Bean
    public SessionFactory getSessionFactory(DataSource dataSource){
        LocalSessionFactoryBuilder builder=new LocalSessionFactoryBuilder(dataSource);
        builder.addProperties(getproperties());
        builder.scanPackages("com.my");
        return builder.buildSessionFactory();
    }


    private Properties getproperties(){
        Properties properties=new Properties();
        properties.put("hibernate.dialect", yamlConfig.getDialect());
        properties.put("hibernate.hbm2ddl.auto", "create");
        return properties;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory){
        HibernateTransactionManager transactionManager=new HibernateTransactionManager(sessionFactory);
            return  transactionManager;
    }
}
