package cn.gjw.shriojwt02;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.gjw.shriojwt02.dao")
@SpringBootApplication
public class Shriojwt02Application {

    public static void main(String[] args) {
        SpringApplication.run(Shriojwt02Application.class, args);
    }

}
