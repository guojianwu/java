package cn.gjw.myjdbc;

import cn.gjw.myjdbc.bean.User;
import cn.gjw.myjdbc.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
class MyjdbcApplicationTests {

    @Autowired
    DataSource dataSource;

    @Autowired
    UserDao userDao;

    @Test
    void contextLoads() {
        System.out.println(dataSource.getClass());
    }

    @Test
    public void test(){
        User userById = userDao.getUserById(1);
        System.out.println(userById);
    }

}
