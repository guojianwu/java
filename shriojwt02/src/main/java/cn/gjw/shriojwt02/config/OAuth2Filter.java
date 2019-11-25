package cn.gjw.shriojwt02.config;

import cn.gjw.shriojwt02.exception.TestEx;
import cn.gjw.shriojwt02.utils.JWTToken;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OAuth2Filter extends AuthenticatingFilter {
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) {
        String token = getRequestToken((HttpServletRequest) servletRequest);

        if (StringUtils.isBlank(token)) {
            return null;
        }
        return new JWTToken(token);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (((HttpServletRequest) request).getMethod().equals(RequestMethod.OPTIONS.name())) {
            return true;
        }

        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        //获取请求token，如果token不存在，直接返回401
        String token = getRequestToken((HttpServletRequest) servletRequest);
        if (StringUtils.isBlank(token)) {
            System.out.println("onAccessDenied token isBlank");
//            HttpServletResponse httpResponse = (HttpServletResponse) response;
//            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
//            httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
//
//            String json = new Gson().toJson(R.error(HttpStatus.SC_UNAUTHORIZED, "invalid token"));
//
//            httpResponse.getWriter().print(json);
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendRedirect("/401");
//            throw new TestEx("ssss");
            return false;
        }

        return executeLogin(servletRequest, servletResponse);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {

        //System.out.println("onLoginFailure");
        HttpServletResponse response1 = (HttpServletResponse) response;
        try {
            response1.sendRedirect("/401");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return false;
//        return super.onLoginFailure(token, e, request, response);

    }

    private String getRequestToken(HttpServletRequest httpRequest) {
        //从header中获取token
        String token = httpRequest.getHeader("token");

        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isBlank(token)) {
            token = httpRequest.getParameter("token");
        }

        return token;
    }
}
