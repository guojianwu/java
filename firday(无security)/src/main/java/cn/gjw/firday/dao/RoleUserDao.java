package cn.gjw.firday.dao;

import cn.gjw.firday.domain.SysRole;
import cn.gjw.firday.domain.SysRoleUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleUserDao {
    int save(SysRoleUser sysRoleUser);
    int updateByUserId(SysRoleUser sysRoleUser);
    int deleteByUserId(Integer userId);


    List<SysRoleUser> findByRoleId(Integer roleId);


}
