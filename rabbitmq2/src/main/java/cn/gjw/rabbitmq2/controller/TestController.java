package cn.gjw.rabbitmq2.controller;


import cn.gjw.rabbitmq2.doamin.Book;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class TestController {



    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/test")
    public String test(Book book){

        book.setName(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("exchange.direct", "atguigu.news", book);
        return "cg";
    }

}
