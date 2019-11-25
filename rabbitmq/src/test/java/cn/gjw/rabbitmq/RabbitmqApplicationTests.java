package cn.gjw.rabbitmq;

import cn.gjw.rabbitmq.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class RabbitmqApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {
        Map<String, Object> map = new HashMap<>();
        map.put("msg","这是第一个消息");
        map.put("datas", Arrays.asList("hello",123,true));
        rabbitTemplate.convertAndSend("exchange.direct", "atguigu.news", new Book("aa","bb"));
    }

    @Test
    void test() {
        Object o = rabbitTemplate.receiveAndConvert("atguigu.news");
        System.out.println("o = " + o);
        System.out.println(o.getClass());
    }


    @Test
    void test2() {
        Map<String, Object> map = new HashMap<>();
        map.put("msg","这是第一个消息");
        map.put("datas", Arrays.asList("hello",123,true));
        rabbitTemplate.convertAndSend("exchange.fanout", "", new Book("aa","fanout"));
    }

}
