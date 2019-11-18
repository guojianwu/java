package cn.gjw.securityjwt.config.security;

import cn.gjw.securityjwt.dao.AuthDao;
import cn.gjw.securityjwt.domain.auth.UserDetail;
import cn.gjw.securityjwt.utils.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * token过滤器来验证token有效性
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    AuthDao authDao;

    @Value("${jwt.header}")
    private String token_header;

    @Value("${jwt.tokenHead}")
    private String bearer;

    @Resource
    private JwtUtils jwtUtils;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader(token_header);

        if(StringUtils.isNotEmpty(token) && token.startsWith(bearer)){
            token=token.substring(bearer.length());
        }else {
            token=null;
        }

        if(token !=null){
            long userIdFromToken = jwtUtils.getUserIdFromToken(token);
            if(userIdFromToken!=0){
                UserDetail userById = authDao.getUserById(userIdFromToken);
                if(userById !=null){
                    UserDetail userDetail = jwtUtils.getUserFromToken(token);
                    if(jwtUtils.validateToken(token,userDetail)){
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
