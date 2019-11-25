package cn.gjw.securityjwt2.dao;

import cn.gjw.firday.domain.SysRoleUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleUserDao {
    int save(SysRoleUser sysRoleUser);

    int updateByUserId(SysRoleUser sysRoleUser);

    int deleteByUserId(Integer userId);


    List<SysRoleUser> findByRoleId(Integer roleId);


}
