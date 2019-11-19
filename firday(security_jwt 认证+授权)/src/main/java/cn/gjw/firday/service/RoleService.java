package cn.gjw.firday.service;

import cn.gjw.firday.dao.PermissionDao;
import cn.gjw.firday.dao.RoleDao;
import cn.gjw.firday.dao.RolePermissionDao;
import cn.gjw.firday.dao.RoleUserDao;
import cn.gjw.firday.domain.SysRole;
import cn.gjw.firday.domain.SysRoleUser;
import cn.gjw.firday.dto.RoleDto;
import cn.gjw.firday.result.PageTableRequest;
import cn.gjw.firday.result.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Transactional
@Service
public class RoleService {
    @Autowired
    RoleDao roleDao;

    @Autowired
    RoleUserDao roleUserDao;

    @Autowired
    RolePermissionDao rolePermissionDao;

    public Results getAllRoles(){
        List<SysRole> allRoles = roleDao.getAllRoles();
        return Results.success(0,allRoles);
    }

    public Results getRoleByPage(PageTableRequest request){
        List<SysRole> roleByPage = roleDao.getRoleByPage(request);
        int i = roleDao.getRoleConut(request).intValue();
        return  Results.success(i,roleByPage);
    }

    public Results save(RoleDto roleDto) {
        roleDao.save(roleDto);
        List<Long> permissionIds = roleDto.getPermissionIds();
        permissionIds.remove(0L);
        if(!CollectionUtils.isEmpty(permissionIds)) {
            rolePermissionDao.saveList(roleDto.getId(), permissionIds);
        }
        return Results.success();
    }


    public Results getRoleById(Integer id){
        return Results.success(roleDao.getRoleById(id));
    }

    public Results update(RoleDto roleDto) {
        roleDao.update(roleDto);
        rolePermissionDao.deleteByRoleId(roleDto.getId());
        List<Long> permissionIds = roleDto.getPermissionIds();
        permissionIds.remove(0L);
        if(!CollectionUtils.isEmpty(permissionIds)) {
            rolePermissionDao.saveList(roleDto.getId(), roleDto.getPermissionIds());
        }
        return Results.success();
    }

    public Results deleteById(Integer id) {
        List<SysRoleUser> roleUsers = roleUserDao.findByRoleId(id);
        if(roleUsers.size()>0){
            return Results.failure(400,"该角色已绑定用户");
        }else {
           roleDao.deleteById(id);
           rolePermissionDao.deleteByRoleId(id);
           return Results.success("删除成功");
        }
    }
}
