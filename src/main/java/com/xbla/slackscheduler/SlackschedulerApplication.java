package com.xbla.slackscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SlackschedulerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SlackschedulerApplication.class, args);
    }

}
