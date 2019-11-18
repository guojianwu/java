package gjw.cn.mybatis01.controller;

import gjw.cn.mybatis01.dao.DeptDao;
import gjw.cn.mybatis01.dao.UserDao;
import gjw.cn.mybatis01.domain.Dept;
import gjw.cn.mybatis01.domain.Jobs;
import gjw.cn.mybatis01.domain.User;
import gjw.cn.mybatis01.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sun.tools.jconsole.JConsole;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserDao userDao;
    @Autowired
    DeptDao deptDao;

    @GetMapping("/allUser")
    public List<User> allUser(){
        return userService.allUser();
    }

    @GetMapping("/addUser2")
    public int addUser2(User user){
        System.out.println(user);
//        String id = UUID.randomUUID().toString();
//        user.setId(id);
//        userService.addUser(user);
        userService.addUser(user);
        System.out.println(user.getId());
        return  user.getId();
    }

    @PostMapping("/addUser")
    public void addUser(@RequestBody User user){
        System.out.println(user);
//        String id = UUID.randomUUID().toString();
//        user.setId(id);
        userService.addUser(user);
    }

    @GetMapping("getUserandDept")
    public List getUserandDept(){
        List<User> userById = userDao.getUserById();
        return userById;
    }

    @GetMapping("getUserandDept2")
    public Dept getUserandDept2(@Param("id") Integer id){
        System.out.println(id);
        Dept deptByIdPlus = deptDao.getDeptByIdPlus(id);
        System.out.println(deptByIdPlus);
        return  deptByIdPlus;
    }

    @GetMapping("/getUserByIf")
    public List<User> getUserByIf(User user){
        return userDao.getUserByIf(user);
    }
}
