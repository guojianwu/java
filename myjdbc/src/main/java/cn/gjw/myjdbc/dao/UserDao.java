package cn.gjw.myjdbc.dao;

import cn.gjw.myjdbc.bean.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
  public User getUserById(Integer id);
}
