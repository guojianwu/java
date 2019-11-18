package cn.gjw.securityjwt2.dao;

import cn.gjw.firday.domain.SysPermission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionDao {
    List<SysPermission> findAll();

    int save(SysPermission sysPermission);

    SysPermission findById(Integer id);

    int update(SysPermission sysPermission);

    List<Integer> getIdsByparentId(Integer id);

    int deleteById(Integer id);

    int deleteByParentId(Integer parentId);
}
