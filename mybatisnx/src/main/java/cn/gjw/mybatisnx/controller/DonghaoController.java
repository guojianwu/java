package cn.gjw.mybatisnx.controller;

import cn.gjw.mybatisnx.domin.Donghao;
import cn.gjw.mybatisnx.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration

@RestController
@EnableConfigurationProperties({Donghao.class})
public class DonghaoController {
    @Autowired
    Donghao donghao;

    @Autowired
    HelloService helloService;

    @Value("${donghao1.name}")
    private String name;

    @RequestMapping("/hello")
    public String hello() {
//        System.out.println(donghao);
        helloService.hello();
        return name;
    }

}
