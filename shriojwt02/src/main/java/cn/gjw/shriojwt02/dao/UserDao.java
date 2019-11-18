package cn.gjw.shriojwt02.dao;

import cn.gjw.shriojwt02.bean.UserBean;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    public UserBean getUser(String username);
}
