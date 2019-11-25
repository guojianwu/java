package cn.gjw.securityjwt.config.security;

import cn.gjw.securityjwt.dao.AuthDao;
import cn.gjw.securityjwt.domain.auth.Role;
import cn.gjw.securityjwt.domain.auth.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * 实现了DetailsService接口，用来做登陆验证
 */
@Component
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    AuthDao authDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetail userDetail = authDao.findByUsername(s);
        if (userDetail == null) {
            throw new UsernameNotFoundException("没有找到username：" + s);
        }
        Role role = authDao.findRoleByUserId(userDetail.getId());
        userDetail.setRole(role);
        return userDetail;
    }
}
