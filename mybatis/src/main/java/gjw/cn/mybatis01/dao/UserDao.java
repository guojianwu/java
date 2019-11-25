package gjw.cn.mybatis01.dao;

import gjw.cn.mybatis01.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao {
    List<User> allUser();

    void addUser(User user);

    List<User> getUserByMap(Map<String, Object> map);

    List<User> getUserById();

    List<User> getUserByIf(User user);

}
