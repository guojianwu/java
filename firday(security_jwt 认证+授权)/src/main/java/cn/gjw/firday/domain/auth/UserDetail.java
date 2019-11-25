package cn.gjw.firday.domain.auth;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetail implements UserDetails {

    private Auth auth;


    private Collection<? extends GrantedAuthority> authorities;

    public UserDetail(Auth auth) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        auth.getSysPermissions().forEach(permission -> {
            if (StringUtils.isNotEmpty(permission.getPermission())) {
                SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getPermission());
                authorities.add(grantedAuthority);
            }
        });
        this.authorities = authorities;
        this.auth = auth;

    }

    public Auth getAuth() {
        return auth;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
    }

    @Override
    public String getPassword() {
        return auth.getPassword();
    }

    @Override
    public String getUsername() {
        return auth.getUsername();
    }

    /**
     * 判断账号是否已经过期，默认没有过期
     *
     * @return true 没有过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 判断账号是否被锁定，默认没有锁定
     *
     * @return true 没有锁定  false 锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return auth.getStatus() == 1;
    }

    /**
     * todo 判断信用凭证是否过期，默认没有过期
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 判断账号是否可用，默认可用
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        return auth.getStatus() == 1;
    }
}
