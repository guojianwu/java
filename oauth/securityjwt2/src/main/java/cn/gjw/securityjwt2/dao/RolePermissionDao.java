package cn.gjw.securityjwt2.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissionDao {
    int saveList(@Param("roleId") Integer id, @Param("permissionIds") List<Long> permissionIds);
    int deleteByRoleId(Integer roleId);
}
