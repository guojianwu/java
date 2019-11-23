package cn.gjw.mybatisplus2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.gjw.mybatisplus2.dao")
@SpringBootApplication
public class Mybatisplus2Application {

    public static void main(String[] args) {
        SpringApplication.run(Mybatisplus2Application.class, args);
    }

}
