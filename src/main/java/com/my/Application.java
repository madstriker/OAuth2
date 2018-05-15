package com.my;

import com.my.commons.YAMLConfig;
import com.my.model.ClientsDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class Application implements CommandLineRunner{

    @Autowired
    private YAMLConfig myConfig;

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void run(String... args) throws Exception {
        System.out.println("driver : " + myConfig.getDriver());
        System.out.println("url: " + myConfig.getUrl());
        System.out.println("username: " + myConfig.getUsername());
        System.out.println("password: " + myConfig.getPassword());
        System.out.println("dialect: " + myConfig.getDialect());
    }

}
