package cn.gjw.securityjwt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("cn.gjw.securityjwt.dao")
@SpringBootApplication
public class SecurityjwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityjwtApplication.class, args);
    }

}
