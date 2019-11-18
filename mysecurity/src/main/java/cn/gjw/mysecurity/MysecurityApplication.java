package cn.gjw.mysecurity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.gjw.mysecurity.dao")
@SpringBootApplication
public class MysecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(MysecurityApplication.class, args);
    }

}
