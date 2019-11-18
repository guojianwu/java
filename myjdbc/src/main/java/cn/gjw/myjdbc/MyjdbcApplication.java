package cn.gjw.myjdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.gjw.myjdbc.dao")
@SpringBootApplication
public class MyjdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyjdbcApplication.class, args);
    }

}
