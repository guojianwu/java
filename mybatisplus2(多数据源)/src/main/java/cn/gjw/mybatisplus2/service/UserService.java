package cn.gjw.mybatisplus2.service;

import cn.gjw.mybatisplus2.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User> {
    User myGetById(Integer id);
}
