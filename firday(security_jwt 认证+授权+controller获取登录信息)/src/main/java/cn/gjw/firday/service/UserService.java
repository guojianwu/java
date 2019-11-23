package cn.gjw.firday.service;

import cn.gjw.firday.dao.RoleUserDao;
import cn.gjw.firday.dao.UserDao;
import cn.gjw.firday.domain.SysRoleUser;
import cn.gjw.firday.domain.SysUser;
import cn.gjw.firday.dto.UserDto;
import cn.gjw.firday.result.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    RoleUserDao roleUserDao;
    public Results<SysUser>  getAllUsersByPage(String name,Integer startPosition,Integer pageSize){
        List<SysUser> allUsersByPage = userDao.getAllUsersByPage(name,startPosition, pageSize);
        Long count = userDao.countAllUsers(name);

        return Results.success(count.intValue(),allUsersByPage);
    }
    public Results save(UserDto userDto){
        if(userDto.getRoleId() !=null){
            userDao.save(userDto);
            SysRoleUser sysRoleUser = new SysRoleUser();
            sysRoleUser.setRoleId(userDto.getRoleId() );
            sysRoleUser.setUserId(userDto.getId().intValue());
            roleUserDao.save(sysRoleUser);
            return Results.success();
        }
        return  Results.failure();
    }

    public Results getUserById(Integer id){
        SysUser userById = userDao.getUserById(id);
        return Results.success(userById);
    }

    public Results updateUserById(SysUser sysUser){
        userDao.updateUserById(sysUser);
        SysRoleUser roleUser = new SysRoleUser();
        roleUser.setUserId(sysUser.getId().intValue());
        roleUser.setRoleId(sysUser.getRoleId());
        roleUserDao.updateByUserId(roleUser);

        return  Results.success();
    }

    public Results deleteUserById(Integer id){
        int i =roleUserDao.deleteByUserId(id);

        if(i>0){
            userDao.deleteUserById(id);
            return Results.success();
        }else {
            return Results.failure();
        }
    }

}
