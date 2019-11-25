package cn.gjw.rabbitmq2;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class Rabbitmq2Application {

    public static void main(String[] args) {
        SpringApplication.run(Rabbitmq2Application.class, args);
    }

}
