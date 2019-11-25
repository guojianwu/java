package cn.gjw.firday.controller;

import cn.gjw.firday.domain.SysUser;
import cn.gjw.firday.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("login")
    public Object login(@RequestBody SysUser sysUser) {
        return authService.login(sysUser.getUsername(), sysUser.getPassword());
    }
}
