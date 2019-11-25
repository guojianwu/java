package cn.gjw.securityjwt2.dao;

import cn.gjw.firday.domain.SysRole;
import cn.gjw.firday.dto.RoleDto;
import cn.gjw.firday.result.PageTableRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {
    List<SysRole> getAllRoles();

    List<SysRole> getRoleByPage(PageTableRequest request);

    Long getRoleConut(PageTableRequest request);

    int save(SysRole sysRole);

    RoleDto getRoleById(Integer id);

    int update(RoleDto roleDto);

    int deleteById(Integer id);
}
