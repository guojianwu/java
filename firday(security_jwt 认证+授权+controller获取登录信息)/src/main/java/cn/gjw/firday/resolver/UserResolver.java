package cn.gjw.firday.resolver;

import cn.gjw.firday.annotation.User;
import cn.gjw.firday.domain.SysUser;
import cn.gjw.firday.util.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 用户信息
 */
public class UserResolver implements HandlerMethodArgumentResolver {

    @Value("${jwt.header}")
    private String token_header;

    @Value("${jwt.tokenHead}")
    private String bearer;
    @Autowired
    JwtUtils jwtUtils;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(User.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
//        String token = request.getHeader("token");
        String token = nativeWebRequest.getHeader(token_header);
        if (StringUtils.isNotEmpty(token) && token.startsWith(bearer)) {
            token = token.substring(bearer.length());
        } else {
            token = null;
        }
        if (StringUtils.isNotEmpty(token)) {
            long userIdFromToken = jwtUtils.getUserIdFromToken(token);
            return userIdFromToken;
        }
        return null;
    }
//    @Override
//    public boolean supportsParameter(MethodParameter methodParameter) {
//        return methodParameter.hasParameterAnnotation(User.class);
//    }
//
//    @Override
//    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
//        if (methodParameter.getParameterType().equals(UserInfoVO.class)) {
//            Cookie[] cookies = nativeWebRequest.getNativeRequest(HttpServletRequest.class).getCookies();
//            if (!ObjectUtils.isEmpty(cookies)) {
//                for (Cookie cookie : cookies) {
//                    if ("token".equals(cookie.getName())) {
//                        String user = redisClient.get(cookie.getValue());
//                        return gson.fromJson(user, UserInfoVO.class);
//                    }
//                }
//            }
//        }
//        return null;
//    }
}