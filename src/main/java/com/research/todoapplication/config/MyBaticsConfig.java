package com.research.todoapplication.config;

import com.research.todoapplication.exception.CustomException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.Reader;

@Configuration
@ComponentScan(basePackages = "com.research.todoapplication")
public class MyBaticsConfig {

    private static final String SQL_MAPPER_CONFIG = "SqlMapperConfig.xml";

    @Bean
    public static SqlSessionFactory getSqlSessionFactory() {
        try {
            Reader reader = Resources.getResourceAsReader(SQL_MAPPER_CONFIG);
            return new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            System.out.println(new CustomException("Error when get sql session factory",  e.getMessage()));
            return null;
        }
    }
}
