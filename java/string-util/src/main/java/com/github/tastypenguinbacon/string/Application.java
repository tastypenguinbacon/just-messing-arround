package com.github.tastypenguinbacon.string;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by pingwin on 01.03.17.
 */
@SpringBootApplication
public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String... args) {
        logger.error("Hello world");
        SpringApplication.run(Application.class, args);
    }
}
