package gjw.cn.mybatis01.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    @RequestMapping("/show")
    public String  show(){
        return "show";
    }
}
