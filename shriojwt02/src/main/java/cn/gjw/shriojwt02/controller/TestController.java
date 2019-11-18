package cn.gjw.shriojwt02.controller;

import cn.gjw.shriojwt02.bean.UserBean;
import cn.gjw.shriojwt02.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    UserDao userDao;

    @RequestMapping("/test")
    public UserBean test(@RequestParam("name") String username){
        System.out.println(username);
        return  userDao.getUser(username);
    }



}
