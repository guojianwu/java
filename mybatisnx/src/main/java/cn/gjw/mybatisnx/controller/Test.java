package cn.gjw.mybatisnx.controller;

import jdk.nashorn.internal.objects.annotations.Property;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@ConfigurationProperties(prefix = "person")
public class Test {


    //    @Value("${person.name}")
    private static String name;

    @Value("${person.name}")
    public void setName(String name) {
        Test.name = name;
    }

    @RequestMapping("/test")
    public String test() {
        System.out.println(name);
        return "nihaoss 0000";
    }
}
