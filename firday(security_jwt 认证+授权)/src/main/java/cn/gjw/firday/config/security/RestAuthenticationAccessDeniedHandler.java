package cn.gjw.firday.config.security;

import cn.gjw.firday.result.ResponseCode;
import cn.gjw.firday.result.Results;
import com.alibaba.fastjson.JSONArray;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 权限不足处理类
 */
@Component
public class RestAuthenticationAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        System.out.println(e.getMessage());
        httpServletResponse.setStatus(200);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        PrintWriter writer = httpServletResponse.getWriter();
//        String body = ResultJson.failure(ResultCode.FORBIDDEN, e.getMessage()).toString();
        String body = JSONArray.toJSON(Results.failure(ResponseCode.FORBIDDEN.getCode(), ResponseCode.FORBIDDEN.getMessage())).toString();
        writer.write(body);
        writer.flush();
    }
}
