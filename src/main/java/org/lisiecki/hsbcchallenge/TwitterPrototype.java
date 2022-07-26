package org.lisiecki.hsbcchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TwitterPrototype {

    public static void main(String[] args) {
        SpringApplication.run(TwitterPrototype.class, args);
    }

}
