package gjw.cn.mybatis01.service.impl;

import gjw.cn.mybatis01.dao.UserDao;
import gjw.cn.mybatis01.domain.User;
import gjw.cn.mybatis01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }


    @Override
    public List<User> allUser() {
        return userDao.allUser();
    }
}
