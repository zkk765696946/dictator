package com.github.liuyuyu.dictator.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/*
 * @author liuyuyu
 */
@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.github.liuyuyu.dictator")
public class DictatorSpringBootStarterDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DictatorSpringBootStarterDemoApplication.class, args)
                .start();
    }
}
