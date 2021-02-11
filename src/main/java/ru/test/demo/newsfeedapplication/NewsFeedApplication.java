package ru.test.demo.newsfeedapplication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class NewsFeedApplication {

    @Value("${POSTGRESQL.ADDON_TIME_ZONE}")
    private String timeZone;

    public static void main(String[] args) {
        SpringApplication.run(NewsFeedApplication.class, args);
    }

    @PostConstruct
    public void started() {
        TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
    }
}
