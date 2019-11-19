package cn.gjw.firday.controller;

import cn.gjw.firday.dto.RoleDto;
import cn.gjw.firday.result.PageTableRequest;
import cn.gjw.firday.result.Results;
import cn.gjw.firday.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("/all")
    public Results getAllRoles(){
        return roleService.getAllRoles();
    }

    @GetMapping("/list")
    public Results getRoleByPage(PageTableRequest request){
        request.countOffset();
        System.out.println(request);
        return roleService.getRoleByPage(request);
    }
    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('sys:role:add','sys:role:edit')")
    public Results save(@RequestBody RoleDto roleDto){
        System.out.println(roleDto);
        if(roleDto.getId() !=null){ //修改
           return roleService.update(roleDto);
        }else { //新增
            return   roleService.save(roleDto);
        }

    }
    @GetMapping("/getRoleById")
    public Results getRoleById(Integer id){
        return roleService.getRoleById(id);
    }

    @PreAuthorize("hasAnyAuthority('sys:role:del')")
    @PostMapping("/delete/{id}")
    public Results delete(@PathVariable("id") Integer id){
        System.out.println(id);
        return roleService.deleteById(id);
    }
}
