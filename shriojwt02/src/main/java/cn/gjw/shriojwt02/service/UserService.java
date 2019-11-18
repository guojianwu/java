package cn.gjw.shriojwt02.service;


import cn.gjw.shriojwt02.bean.UserBean;
import cn.gjw.shriojwt02.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public UserBean getUser(String username) {
        UserBean userBean = userDao.getUser(username);
        return  userBean;
    }
}
