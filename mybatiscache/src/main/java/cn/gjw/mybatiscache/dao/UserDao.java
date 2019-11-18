package cn.gjw.mybatiscache.dao;

import cn.gjw.mybatiscache.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    User getUserById(Integer id);
}
