package cn.gjw.mysecurity;

import cn.gjw.mysecurity.dao.TbPermissionDao;
import cn.gjw.mysecurity.dao.TbUserDao;
import cn.gjw.mysecurity.domin.TbPermission;
import cn.gjw.mysecurity.domin.TbUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MysecurityApplicationTests {

    @Autowired
    TbUserDao tbUserDao;

    @Autowired
    TbPermissionDao tbPermissionDao;

    @Test
    void contextLoads() {
//        TbUser admin = tbUserDao.getUserByName("admin");
//        System.out.println(admin);
        List<TbPermission> userById = tbPermissionDao.getUserById(37);
        System.out.println(userById);
    }

}
