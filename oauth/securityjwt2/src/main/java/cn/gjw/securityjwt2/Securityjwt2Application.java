package cn.gjw.securityjwt2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.gjw.securityjwt2.dao")
@SpringBootApplication
public class Securityjwt2Application {

    public static void main(String[] args) {
        SpringApplication.run(Securityjwt2Application.class, args);
    }

}
