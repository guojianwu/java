package cn.gjw.myjdbc.controller;

import cn.gjw.myjdbc.bean.User;
import cn.gjw.myjdbc.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    UserDao userDao;

    @GetMapping("/test")
    public User test(){
        User user = userDao.getUserById(1);
        System.out.println(user);
        return user;
    }
}
