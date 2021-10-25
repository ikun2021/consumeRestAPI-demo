package com.example.consumerestapi;

import com.example.consumerestapi.entity.Joke;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
public class ConsumeRestApiApplication {

    public static void main(String[] args) {

        SpringApplication.run(ConsumeRestApiApplication.class, args);
    }

    //方法放入Ioc容器，生成RestTemplate
    @Bean
    public RestTemplate getRestTemplate(RestTemplateBuilder builder){
        return builder.build();
    }


    //CommandLineRunner和ApplicationRunner是Spring Boot所提供的接口，他们都有一个run()方法。
    //所有实现他们的Bean都会在Spring Boot服务启动之后自动地被调用。
    @Bean
    public CommandLineRunner run(RestTemplate restTemplate){
        //使用RestTemplate调用第三方rest api
        //pom.xml添加jackson-databind依赖才能将json数据转成java Object
        return args -> {
            Joke[] jokes = restTemplate.getForObject("https://official-joke-api.appspot.com/random_ten",Joke[].class);
            System.out.println(jokes);
        };
    }

}

