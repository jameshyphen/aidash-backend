package com.aidash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

import com.aidash.common.config.YamlPropertySourceFactory;

@SpringBootApplication
@ComponentScan(basePackages = "com.aidash")
@PropertySource(value = { "classpath:application.yml",
        "classpath:repository.yml" }, factory = YamlPropertySourceFactory.class)
public class AiDashApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiDashApplication.class, args);
    }
}
