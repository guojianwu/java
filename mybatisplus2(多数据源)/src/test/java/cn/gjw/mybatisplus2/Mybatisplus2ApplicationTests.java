package cn.gjw.mybatisplus2;

import cn.gjw.mybatisplus2.dao.UserDao;
import cn.gjw.mybatisplus2.domain.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Mybatisplus2ApplicationTests {

    @Autowired
    UserDao userDao;

    @Test
    void contextLoads() {
//        List<User> users = userDao.selectList(null);
//        users.forEach(System.out::println);

        IPage<User> page = new Page<>();
        page.setCurrent(1);
        page.setSize(2);

        IPage<User> page1 = userDao.selectPage(page, null);
        long size = page1.getSize();
        long total = page1.getTotal();
        System.out.println(size);
        System.out.println(total);
        List<User> records = page1.getRecords();
        records.forEach(System.out::println);

    }

}
