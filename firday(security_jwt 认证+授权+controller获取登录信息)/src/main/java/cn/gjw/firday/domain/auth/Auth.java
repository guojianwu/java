package cn.gjw.firday.domain.auth;

import cn.gjw.firday.domain.SysPermission;
import cn.gjw.firday.domain.SysUser;
import lombok.Data;

import java.util.List;

@Data
public class Auth extends SysUser {
    private List<SysPermission> sysPermissions;
}
