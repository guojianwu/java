package cn.gjw.firday;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("cn.gjw.firday.dao")
@SpringBootApplication
public class FirdayApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirdayApplication.class, args);
    }

}
