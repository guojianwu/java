package cn.gjw.httpclient.controller;

import cn.gjw.httpclient.util.CheckoutUtil;
import cn.gjw.httpclient.util.Util;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/wxtoken")
public class WxToken {
//    Util util = new Util();

    /**
     * 微信消息接收和token验证
     * @param model
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = { "/get" }, method = RequestMethod.GET)
    public String signature(
            @RequestParam(value = "signature", required = true) String signature,
            @RequestParam(value = "timestamp", required = true) String timestamp,
            @RequestParam(value = "nonce", required = true) String nonce,
            @RequestParam(value = "echostr", required = true) String echostr,
            HttpServletResponse response) throws IOException {
        String[] values = { "ggggggg", timestamp, nonce };
        Arrays.sort(values); // 字典序排序
        String value = values[0] + values[1] + values[2];
        String sign = DigestUtils.shaHex(value);
        PrintWriter writer = response.getWriter();
        System.out.println(signature.equals(sign));
        if (signature.equals(sign)) {// 验证成功返回ehcostr
//            writer.print(echostr);
            return  echostr;
        } else {
            writer.print("error");
        }
        writer.flush();
        writer.close();
        return  null;
    }
//    @RequestMapping(value = { "/get" }, method = { RequestMethod.POST, RequestMethod.GET })
//    public void get(@RequestBody(required = false) String reqDate, HttpServletRequest request,
//                    HttpServletResponse response) throws Exception {
//
//        String echostr = request.getParameter("echostr");
//        System.out.println(echostr);
//        PrintWriter print;
//        print = response.getWriter();
//        print.write(echostr);
//        print.flush();
//
//    }
//    @RequestMapping(value = { "/get" }, method = { RequestMethod.POST, RequestMethod.GET })
//    public void get(@RequestBody(required = false) String reqDate, HttpServletRequest request,
//                    HttpServletResponse response) throws Exception {
//        boolean isGet = request.getMethod().toLowerCase().equals("get");
//        PrintWriter print;
//        System.out.println(isGet);
//        if (isGet) {
//            // 微信加密签名
//            String signature = request.getParameter("signature");
//            // 时间戳
//            String timestamp = request.getParameter("timestamp");
//            // 随机数
//            String nonce = request.getParameter("nonce");
//            // 随机字符串
//            String echostr = request.getParameter("echostr");
//            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
//            if (signature != null && CheckoutUtil.checkSignature(signature, timestamp, nonce)) {
//                try {
//                    print = response.getWriter();
//                    print.write(echostr);
//                    print.flush();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

}
