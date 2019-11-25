package cn.gjw.firday.config.security;

import cn.gjw.firday.dao.AuthDao;
import cn.gjw.firday.domain.auth.Auth;
import cn.gjw.firday.domain.auth.UserDetail;
import cn.gjw.firday.util.JwtUtils;
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
import java.util.ArrayList;

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

        if (StringUtils.isNotEmpty(token) && token.startsWith(bearer)) {
            token = token.substring(bearer.length());
        } else {
            token = null;
        }

        if (token != null) {
            long userIdFromToken = jwtUtils.getUserIdFromToken(token);
//            String usernameFromToken = jwtUtils.getUsernameFromToken(token);
//            System.out.println(userIdFromToken);
//            System.out.println(usernameFromToken);
//            Auth userFromToken = jwtUtils.getUserFromToken(token);
//            System.out.println(userIdFromToken);
            if (userIdFromToken != 0) {
                Auth userById = authDao.getUserById(userIdFromToken);
//                httpServletResponse.g
                if (userById != null) {
                    UserDetail userDetail = new UserDetail(userById);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userById, null, userDetail.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                    UserDetail userByIdrDetail = jwtUtils.getUserFromToken(token);
//                    if(jwtUtils.validateToken(token,userDetail)){
//                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
//                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
//                        SecurityContextHolder.getContext().setAuthentication(authentication);
//                    }
                }
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
