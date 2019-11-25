package gjw.cn.mybatis01.service;

import gjw.cn.mybatis01.domain.Jobs;
import gjw.cn.mybatis01.domain.User;

import java.util.List;

public interface UserService {
    public List<User> allUser();

    public void addUser(User user);
}
