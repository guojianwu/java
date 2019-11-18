package cn.gjw.mybatiscache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.gjw.mybatiscache.dao")
@SpringBootApplication
public class MybatiscacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatiscacheApplication.class, args);
    }

}
