package cn.gjw.rabbitmq.controller;

import cn.gjw.rabbitmq.domain.Book;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class BookController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/test")
    public String test(Book book){
//        rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",new Book("ss","cc"));
        book.setName(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("exchange.direct", "atguigu.news", book);
        return "cg";

    }

}
