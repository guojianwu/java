package cn.gjw.firday.controller;

import cn.gjw.firday.domain.SysPermission;
import cn.gjw.firday.result.Results;
import cn.gjw.firday.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    PermissionService permissionService;

    @RequestMapping("/findAll")
    public Results findAll() {
        return permissionService.findAll();
    }

    @GetMapping("/findById")
    public Results findById(Integer id) {
        return permissionService.findById(id);
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('sys:menu:edit','sys:menu:add')")
    public Results save(@RequestBody SysPermission sysPermission) {
        System.out.println(sysPermission);
        if (sysPermission.getId() == null) { //新增
            return permissionService.save(sysPermission);
        } else { //修改

//            return null;
            return permissionService.update(sysPermission);
        }
    }

    @PreAuthorize("hasAnyAuthority('sys:menu:del')")
    @PostMapping("/delete/{id}")
    public Results delete(@PathVariable("id") Integer id) {
        return permissionService.delate(id);
    }

//    public Results getMenuAll(){
//        permissionService.getMenuAll();
//        return  null;
//    }
}
