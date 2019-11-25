package cn.gjw.firday.service;

import cn.gjw.firday.config.security.MyUserDetailsService;
import cn.gjw.firday.dao.AuthDao;
import cn.gjw.firday.domain.auth.Auth;
import cn.gjw.firday.domain.auth.UserDetail;
import cn.gjw.firday.exception.CustomException;
import cn.gjw.firday.result.ResponseCode;
import cn.gjw.firday.result.Results;
import cn.gjw.firday.util.JwtUtils;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailsService myUserDetailsService;


    @Autowired
    private JwtUtils jwtTokenUtil;
//    private final AuthMapper authMapper;

    @Autowired
    private AuthDao authDao;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    public Object login(String username, String password) {

        final Authentication authentication = authenticate(username, password);
        //存储认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);

        System.out.println(authentication);
        //生成token
//        final UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        UserDetail principal = (UserDetail) authentication.getPrincipal();
        Auth userByUsername = principal.getAuth();
//        Collection<? extends GrantedAuthority> authorities = principal.getAuthorities();
//        Auth userByUsername = authDao.getUserByUsername(username);
//        System.out.println(userByUsername);
        final String token = jwtTokenUtil.generateAccessToken(userByUsername);
        System.out.println(token);
        //存储token
//        jwtTokenUtil.putToken(username, token);
//        return new ResponseUserToken(token, userDetail);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("user", userByUsername);
        return Results.success(map);
    }

    private Authentication authenticate(String username, String password) {
        System.out.println(password);
        try {
            //该方法会去调用userDetailsService.loadUserByUsername()去验证用户名和密码，如果正确，则存储该用户名密码到“security 的 context中”
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException | BadCredentialsException e) {
            String body = JSONArray.toJSON(Results.failure(ResponseCode.LOGIN_ERROR.getCode(), ResponseCode.LOGIN_ERROR.getMessage())).toString();

            throw new CustomException(Results.failure(ResponseCode.LOGIN_ERROR.getCode(), ResponseCode.LOGIN_ERROR.getMessage()));
        }
    }
}
