package cn.gjw.mybatisplus2.dao;

import cn.gjw.mybatisplus2.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends BaseMapper<User> {
    User myGetById(Integer id);
}
