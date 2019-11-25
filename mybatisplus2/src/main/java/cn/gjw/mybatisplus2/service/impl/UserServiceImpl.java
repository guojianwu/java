package cn.gjw.mybatisplus2.service.impl;

import cn.gjw.mybatisplus2.dao.UserDao;
import cn.gjw.mybatisplus2.domain.User;
import cn.gjw.mybatisplus2.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;


@Service
@Cacheable(value = {"user"})
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Autowired
    UserDao userDao;


    public User myGetById(Integer id) {
        return userDao.myGetById(id);
    }
}
