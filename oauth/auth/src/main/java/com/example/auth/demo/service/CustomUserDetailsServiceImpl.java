package com.example.auth.demo.service;

import com.example.auth.demo.domain.auth.Role;
import com.example.auth.demo.domain.auth.UserDetail;
import com.example.auth.demo.mapper.AuthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 登陆身份认证
 * @author: JoeTao
 * createAt: 2018/9/14
 */
@Service(value = "CustomUserDetailsService")
public class CustomUserDetailsServiceImpl implements UserDetailsService {
//    private final AuthMapper authMapper;

    @Autowired
    private  AuthMapper authMapper;
//    public CustomUserDetailsServiceImpl(AuthMapper authMapper) {
//        this.authMapper = authMapper;
//    }

    @Override
    public UserDetail loadUserByUsername(String name) throws UsernameNotFoundException {
        UserDetail userDetail = authMapper.findByUsername(name);
        if (userDetail == null) {
            throw new UsernameNotFoundException(String.format("No userDetail found with username '%s'.", name));
        }
        Role role = authMapper.findRoleByUserId(userDetail.getId());
        userDetail.setRole(role);
        return userDetail;
    }
}
