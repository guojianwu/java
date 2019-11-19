package cn.gjw.firday.config.security;

import cn.gjw.firday.dao.AuthDao;
import cn.gjw.firday.domain.auth.Auth;
import cn.gjw.firday.domain.auth.UserDetail;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现了DetailsService接口，用来做登陆验证
 */
@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    AuthDao authDao;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println(s);
        Auth auth = authDao.getUserByUsername(s);

        System.out.println(auth);

        if(auth == null){
            throw new UsernameNotFoundException("没有找到username："+s);
        }
//        Role role = authDao.findRoleByUserId(userDetail.getId());
//        userDetail.setRole(role);
//        return new User(userDetail.getUsername(),userDetail.getPassword(),new ArrayList<>());

//        List<GrantedAuthority> authorities = new ArrayList<>();
//        auth.getSysPermissions().forEach(permission -> {
//            if(StringUtils.isNotEmpty(permission.getPermission())){
//                SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getPermission());
//                authorities.add(grantedAuthority);
//            }
//
//        });
//        System.out.println(authorities);

        UserDetail userDetail = new UserDetail(auth);
        System.out.println(userDetail);
        return userDetail;

//        return new User(userDetail.getUsername(),userDetail.getPassword(),authorities);
    }
}
