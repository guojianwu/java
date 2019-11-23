package cn.gjw.mybatisplus2;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.gjw.mybatisplus2.dao")
@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class})
public class Mybatisplus2Application {

    public static void main(String[] args) {
        SpringApplication.run(Mybatisplus2Application.class, args);
    }

}
