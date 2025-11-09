package br.com.letscode.zoo.config;

import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class BaseConfig {

    /*@Bean
    public Category getCategory() {
        return new Category(2);
    }*/

    @PostConstruct
    public void init() {
        System.out.println("Config iniciou!!");
    }
}
