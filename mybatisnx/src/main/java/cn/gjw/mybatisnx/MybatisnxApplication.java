package cn.gjw.mybatisnx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"cn.gjw.mybatisnx.dao"})
public class MybatisnxApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisnxApplication.class, args);
    }

}
