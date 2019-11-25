package cn.gjw.mybatiscache.service;

import cn.gjw.mybatiscache.dao.UserDao;
import cn.gjw.mybatiscache.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

@EnableCaching
@Service
public class UserService {
    @Autowired
    UserDao userDao;

    @Cacheable(cacheNames = {"user"})
    public User getUserById(Integer id) {
        System.out.println("select id:" + id);
        return userDao.getUserById(id);
    }
}
