package gjw.cn.mybatis01;

import gjw.cn.mybatis01.dao.DeptDao;
import gjw.cn.mybatis01.dao.UserDao;
import gjw.cn.mybatis01.domain.Dept;
import gjw.cn.mybatis01.domain.User;
import gjw.cn.mybatis01.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Mybatis01ApplicationTests {

    @Autowired
    UserService userService;

    @Autowired
    DeptDao deptDao;

    @Autowired
    UserDao userDao;

    @Test
    public void contextLoads() {
//        String s = UUID.randomUUID().toString();
//        System.out.println(s);
        List<User> users = userService.allUser();
        for (User u : users) {
            System.out.println(u);
        }
    }

    @Test
    public void test01() {
        User user = new User();
        String s = UUID.randomUUID().toString();
//        System.out.println(s);
//        user.setId(s);
        user.setUsername("haha");
        user.setPassword("111111");
        userService.addUser(user);
    }

    @Test
    public void test02() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("username", "aa");
        List<User> userByMap = userDao.getUserByMap(map);
        System.out.println(userByMap);
    }

    @Test
    public void test03() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("username", "aa");
        List<User> userByMap = userDao.getUserById();
        System.out.println(userByMap);
    }

    @Test
    public void test04() {
        Dept dept = deptDao.getDeptById(1);
        System.out.println(dept);
    }

    @Test
    public void test05() {
        Dept dept = deptDao.getDeptByIdPlus(1);
        System.out.println(dept);
    }
}
