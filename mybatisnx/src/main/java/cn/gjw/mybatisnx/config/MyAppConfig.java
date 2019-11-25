package cn.gjw.mybatisnx.config;

import cn.gjw.mybatisnx.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyAppConfig {

    @Bean
    public HelloService helloService() {
        System.out.println("MyAppConfig");
        return new HelloService();
    }
}
