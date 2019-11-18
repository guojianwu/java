package cn.gjw.securityjwt2.dto;

import cn.gjw.securityjwt2.domain.SysRole;
import lombok.Data;

import java.util.List;

@Data
public class RoleDto extends SysRole {

    private static final long serialVersionUID = 998772243417516006L;
    private List<Long> permissionIds;
}
