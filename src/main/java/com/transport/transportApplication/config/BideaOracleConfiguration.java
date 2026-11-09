package com.transport.transportApplication.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ConditionalOnProperty(prefix = "bidea.datasource", name = "url")
public class BideaOracleConfiguration {

    @Bean
    DataSource bideaDataSource(
            @Value("${bidea.datasource.url}") String url,
            @Value("${bidea.datasource.username:}") String username,
            @Value("${bidea.datasource.password:}") String password) {
        return DataSourceBuilder.create()
                .driverClassName("oracle.jdbc.OracleDriver")
                .url(url)
                .username(username)
                .password(password)
                .build();
    }
}
