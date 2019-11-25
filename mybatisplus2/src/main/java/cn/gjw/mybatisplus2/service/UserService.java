package cn.gjw.mybatisplus2.service;

import cn.gjw.mybatisplus2.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;


public interface UserService extends IService<User> {
    User myGetById(Integer id);
}
