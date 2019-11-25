package cn.gjw.rabbitmq.service;

import cn.gjw.rabbitmq.domain.Book;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @RabbitListener(queues = "atguigu.news")
    public void receive(Book book){
        System.out.println(book);
    }
}
