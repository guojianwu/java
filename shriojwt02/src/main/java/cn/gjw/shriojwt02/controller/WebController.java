package cn.gjw.shriojwt02.controller;

import cn.gjw.shriojwt02.bean.ResponseBean;
import cn.gjw.shriojwt02.bean.UserBean;
import cn.gjw.shriojwt02.exception.TestEx;
import cn.gjw.shriojwt02.service.UserService;
import cn.gjw.shriojwt02.utils.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebController {
    @Autowired
    private UserService userService;

//    @Autowired
//    public void setService(UserService userService) {
//        this.userService = userService;
//    }
    @PostMapping("/401")
    public String test(){
        return "my 401";
    }

    @RequestMapping("/test02")
    public String test02(){
        return "test02";
    }


    @PostMapping("/login")
    public ResponseBean login(@RequestBody UserBean user) throws TestEx {
        System.out.println(user);
        if(user.getUsername()==null){
            throw new TestEx("参数错误");
        }

        UserBean userBean = userService.getUser(user.getUsername());
        if(userBean==null){
            throw new TestEx("没有该用户");
        }
        if(!userBean.getPassword().equals(user.getPassword())){
            throw new TestEx("密码错误");
        }
//        if (userBean.getPassword().equals(user.getPassword())) {
//            return new ResponseBean(200, "Login success", JWTUtil.sign(user.getUsername(), user.getPassword()));
//        } else {
//            return new ResponseBean(401,"aaa",null);
////            throw new UnauthorizedException();
//        }
        return new ResponseBean(200, "Login success", JWTUtil.sign(userBean.getUsername(), userBean.getPassword()));
    }

    @GetMapping("/article")
    public ResponseBean article() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return new ResponseBean(200, "You are already logged in", null);
        } else {
            return new ResponseBean(200, "You are guest", null);
        }
    }

    @GetMapping("/require_auth")
    @RequiresAuthentication
    public ResponseBean requireAuth() {
        return new ResponseBean(200, "You are authenticated", null);
    }

    @GetMapping("/require_role")
    @RequiresRoles("admin")
    public ResponseBean requireRole() {
        return new ResponseBean(200, "You are visiting require_role", null);
    }

    @GetMapping("/require_permission")
    @RequiresPermissions(logical = Logical.AND, value = {"view", "edit"})
    public ResponseBean requirePermission() {
        return new ResponseBean(200, "You are visiting permission require edit,view", null);
    }


//    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @RequestMapping(path = "/401")
    public ResponseBean unauthorized()  {
//        throw new TestEx("fghjkl");
        return new ResponseBean(401, "Unauthorized", null);
    }

}
