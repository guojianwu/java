package cn.gjw.mybatisplus2;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("cn.gjw.mybatisplus2.dao")
@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class})
@EnableScheduling
@EnableCaching
public class Mybatisplus2Application {

    public static void main(String[] args) {
        SpringApplication.run(Mybatisplus2Application.class, args);
    }

}
