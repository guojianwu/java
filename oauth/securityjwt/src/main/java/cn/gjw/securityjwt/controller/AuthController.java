package cn.gjw.securityjwt.controller;


import cn.gjw.securityjwt.domain.ResultCode;
import cn.gjw.securityjwt.domain.ResultJson;
import cn.gjw.securityjwt.domain.auth.ResponseUserToken;
import cn.gjw.securityjwt.domain.auth.Role;
import cn.gjw.securityjwt.domain.auth.User;
import cn.gjw.securityjwt.domain.auth.UserDetail;
import cn.gjw.securityjwt.service.AuthService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author JoeTao
 * createAt: 2018/9/17
 */

@RestController
@RequestMapping("/v1")
public class AuthController {
    @Value("${jwt.header}")
    private String tokenHeader;

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/login")
    public ResultJson<ResponseUserToken> login(
            @Valid @RequestBody User user) {
        final ResponseUserToken response = authService.login(user.getName(), user.getPassword());
        return ResultJson.ok(response);
    }

    @GetMapping(value = "/logout")
    public ResultJson logout(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        authService.logout(token);
        return ResultJson.ok();
    }

    @GetMapping(value = "/user")
    public ResultJson getUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
//        if (token == null) {
//            return ResultJson.failure(ResultCode.UNAUTHORIZED);
//        }
        UserDetail userDetail = authService.getUserByToken(token);
        return ResultJson.ok(userDetail);
    }

    @PostMapping(value = "/sign")
    public ResultJson sign(@RequestBody User user) {
        if (StringUtils.isAnyBlank(user.getName(), user.getPassword())) {
            return ResultJson.failure(ResultCode.BAD_REQUEST);
        }
        UserDetail userDetail = new UserDetail(user.getName(), user.getPassword(), Role.builder().id(1l).build());
        return ResultJson.ok(authService.register(userDetail));
    }
//    @GetMapping(value = "refresh")
//    @ApiOperation(value = "刷新token")
//    public ResultJson refreshAndGetAuthenticationToken(
//            HttpServletRequest request){
//        String token = request.getHeader(tokenHeader);
//        ResponseUserToken response = authService.refresh(token);
//        if(response == null) {
//            return ResultJson.failure(ResultCode.BAD_REQUEST, "token无效");
//        } else {
//            return ResultJson.ok(response);
//        }
//    }
}
