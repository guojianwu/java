package cn.gjw.securityjwt2.dao;

import cn.gjw.firday.domain.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    Long countAllUsers(String name);

    List<SysUser> getAllUsersByPage(@Param("name") String name, @Param("startPosition") Integer startPosition, @Param("pageSize") Integer pageSize);

    int save(SysUser sysUser);

    SysUser getUserById(Integer id);

    int updateUserById(SysUser userDto);

    int deleteUserById(Integer id);
}
