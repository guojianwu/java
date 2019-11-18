package cn.gjw.securityjwt2.domain;

import lombok.Data;

@Data
public class RolePermission {
    private Integer roleId;
    private Integer permissionId;
}
