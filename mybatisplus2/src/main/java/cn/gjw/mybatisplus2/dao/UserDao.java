package cn.gjw.mybatisplus2.dao;


import cn.gjw.mybatisplus2.domain.User;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.stereotype.Repository;

@DS("db2-slave")
@Repository
public interface UserDao extends BaseMapper<User> {
    User myGetById(Integer id);
}
