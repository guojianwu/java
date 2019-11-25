package cn.gjw.firday.controller;

import cn.gjw.firday.domain.SysRole;
import cn.gjw.firday.domain.SysUser;
import cn.gjw.firday.dto.UserDto;
import cn.gjw.firday.result.PageTableRequest;
import cn.gjw.firday.result.Results;
import cn.gjw.firday.service.UserService;
import cn.gjw.firday.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/list")
    public Results<SysUser> getUsers(PageTableRequest request) {
        request.countOffset();
        return userService.getAllUsersByPage(request.getName(), request.getOffset(), request.getPageSize());
    }

    @GetMapping("/{id}")
    public Results getUserById(@PathVariable("id") Integer id) {

        return userService.getUserById(id);
    }


    @PostMapping("/save")
    public Results save(@RequestBody UserDto userDto) {
        System.out.println(userDto);
        if (userDto.getPassword() != null) {
            userDto.setPassword(MD5.crypt(userDto.getPassword()));
        }
        if (userDto.getId() != null) { //更新
            return userService.updateUserById(userDto);
        } else { //保存
            userDto.setStatus(1);
            return userService.save(userDto);
        }
    }


    @PostMapping("/delete")
    public Results deleteUser(@RequestBody UserDto userDto) {
        int id = userDto.getId().intValue();
        System.out.println(id);
        return userService.deleteUserById(id);
    }

//    String pattern = "yyyy-MM-dd";
//
//    //只需要加上下面这段即可，注意不能忘记注解
//    @InitBinder
//    public void initBinder(WebDataBinder binder, WebRequest request) {
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat(pattern), true));// CustomDateEditor为自定义日期编辑器
//    }

}
