package com.plurasight.conferencedemo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConferenceDemo2Application {
    // because we have many-to-many relationships we have a cyclical problem we could solve it with annotations or with DTOs (data transfer object
    // hanzwd annotations that will prevent it from back serialization back to the session  @JsonIgnore
    public static void main(String[] args) {
        SpringApplication.run(ConferenceDemo2Application.class, args);
    }

}
