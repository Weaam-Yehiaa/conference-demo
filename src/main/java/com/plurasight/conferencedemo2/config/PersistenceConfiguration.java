package com.plurasight.conferencedemo2.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class PersistenceConfiguration {
    // any methods that we define in here can return been definitions that will get stored in Spring context
    @Bean
    // when it returns dataSource object ,spring looks for it and tries to see if one already are exists if it does, it will replace the definition with the one that it found
    public DataSource dataSource()
    {
        DataSourceBuilder builder=DataSourceBuilder.create();
        builder.url("jdbc:postgresql://localhost:5432/conference_app");
        // ask the builder to go to build dataSource with this URL and return it & we specify it as a bean
        System.out.println("My custom DataSource has been initialized and set  ");
        return builder.build();
        //Spring will look for that bean in the Spring context and replace the DataSource definition with what we've been implemented here
    }
}
