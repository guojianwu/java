package cn.gjw.httpclient.controller;

import cn.gjw.httpclient.result.HttpResult;
import cn.gjw.httpclient.service.HttpAPIService;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.Buffer;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/wx")
public class HttpClientController {
    @Autowired
    private HttpAPIService httpAPIService;

    @RequestMapping("httpclient")
    public String test() throws Exception {
        String str = httpAPIService.doGet("http://www.baidu.com");
        System.out.println(str);
        return str;
    }
    @RequestMapping("/wxConfig")
    public Map  wxConfig(@RequestParam("url") String shareUrl) throws Exception {
        System.out.println(shareUrl);
//        String accessToken = this.getJSSDKAccessToken();
//        String jsapiTicket = this.getJSSDKJsapiTicket(accessToken);
        String accessToken="26_stjdGZbQS8jW6PGAVT9fkXC1qRiF5j99TlqQmVg9JeKMtl-bmnNSezKIfuCqk6_FVd-27AKOrBoPU8w90A4Ez1LtG3Ku1HhCxG4g8LkOivEg9GtBvDC8WhxYZIo7he5YpeLmfd9YXekD7v__HDHhAGAVRV";
        String jsapiTicket ="HoagFKDcsGMVCIY2vOjf9jpY341bmCib-CUzrN6cvVgPWcfAecZiRg8pJCvGIktCi0h6_blxnZADE5O5TsgKZA";
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        String nonceStr = UUID.randomUUID().toString();
        String signature = this.buildJSSDKSignature(jsapiTicket,timestamp,nonceStr,shareUrl);

        Map<String,String> map = new HashMap<String,String>();
        map.put("shareUrl", shareUrl);
        map.put("jsapi_ticket", jsapiTicket);
        map.put("nonceStr", nonceStr);
        map.put("timestamp", timestamp);
        map.put("signature", signature);
        map.put("appid", "wxf8611197fe61cf64");
        return map;
    }
    public static String buildJSSDKSignature(String ticket,String timestamp,String nonceStr ,String url) throws Exception {

        String orderedString = "jsapi_ticket=" + ticket
                + "&noncestr=" + nonceStr + "&timestamp=" + timestamp
                + "&url=" + url;
        return sha1(orderedString);
    }
    public static String sha1(String orderedString) throws Exception {
        String ciphertext = null;
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] digest = md.digest(orderedString.getBytes());
        ciphertext = byteToStr(digest);
        return ciphertext.toLowerCase();
    }
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }
    private static String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];

        String s = new String(tempArr);
        return s;
    }


    @RequestMapping("/getOpenId")
    public String getOpenId(@RequestParam("code") String code) throws Exception {
        String url="https://api.weixin.qq.com/sns/jscode2session?appid=wx2136b8df44e2fbce&secret=162941c365eb5b3c96a85da69de480c8&js_code="+code+"&grant_type=authorization_code";
        String str = httpAPIService.doGet(url);
        System.out.println(str);
        System.out.println(code);
        return str;
    }
    //24_VLUdrt3edAf4HZF3sjmSRuIKm-NqKo-8xZfTTuvlsz40_p1HVWNBJC-L5kyL1NTEkf7J96STio4ZNFIentw5JM8s5mMO6UUrXva7yNKwhcSXMBLl2XgJ9vh2DLoJICgAJADCW
    @RequestMapping("/getAccessToken")
    public String getAccessToken() throws Exception {
        String accessTokenUrl="https://api.weixin.qq.com/cgi-bin/token";
        HashMap<String, Object> map = new HashMap<>();
        map.put("grant_type","client_credential");
        map.put("appid","wx2136b8df44e2fbce");
        map.put("secret","162941c365eb5b3c96a85da69de480c8");
        String result = httpAPIService.doGet(accessTokenUrl, map);

        return  result;
    }



    @RequestMapping("/createQRCode")
    public String createQRCode() throws Exception {
        String accessTokenUrl="https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token=24_39kUjDjc8o7fHCYFUp51t62j3q6MFAy2c_gtIewpL9xCZ72HolhFKyW5hwc9HoYTXPXIynQLxjymCwt9vb23JArdRWvOmE30T_ci_rdWPDFY_a05VLxFMAsPPgOB2h-QD9TUl2suFpzSZ30CUXCcAJAMPU";
        HashMap<String, Object> map = new HashMap<>();
        map.put("path","pages/index/index?openid=12222222222");
        InputStream inputStream = httpAPIService.doPostJson(accessTokenUrl, map);
        saveToImgByInputStream(inputStream,"img/"+UUID.randomUUID().toString()+".png");
        return  "test";
    }

    @RequestMapping("/wxa")
    public String wxa() throws Exception {
        String accessTokenUrl="https://api.weixin.qq.com/wxa/getwxacode?access_token=24_39kUjDjc8o7fHCYFUp51t62j3q6MFAy2c_gtIewpL9xCZ72HolhFKyW5hwc9HoYTXPXIynQLxjymCwt9vb23JArdRWvOmE30T_ci_rdWPDFY_a05VLxFMAsPPgOB2h-QD9TUl2suFpzSZ30CUXCcAJAMPU";
        HashMap<String, Object> map = new HashMap<>();
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("r",250);
        map2.put("g",0);
        map2.put("b",0);
        map.put("path","pages/index/index");

        map.put("line_color",map2);
        map.put("is_hyaline",true);
        InputStream inputStream = httpAPIService.doPostJson(accessTokenUrl, map);
        System.out.println(inputStream);
        saveToImgByInputStream(inputStream,"img/"+UUID.randomUUID().toString()+".png");
        return  "test";
    }

    @RequestMapping("/getwxacodeunlimit")
    public String getwxacodeunlimit() throws Exception {
        String accessTokenUrl="https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=24_39kUjDjc8o7fHCYFUp51t62j3q6MFAy2c_gtIewpL9xCZ72HolhFKyW5hwc9HoYTXPXIynQLxjymCwt9vb23JArdRWvOmE30T_ci_rdWPDFY_a05VLxFMAsPPgOB2h-QD9TUl2suFpzSZ30CUXCcAJAMPU";
        HashMap<String, Object> map = new HashMap<>();
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("r",250);
        map2.put("g",0);
        map2.put("b",0);
        map.put("scene","1122");
        map.put("path","pages/index/index?openid=122223333333");
        map.put("line_color",map2);
        map.put("is_hyaline",true);
        InputStream inputStream = httpAPIService.doPostJson(accessTokenUrl, map);
        System.out.println(inputStream);
        saveToImgByInputStream(inputStream,"img/"+UUID.randomUUID().toString()+".png");
        return  "test";
    }

    public static int saveToImgByInputStream(InputStream instreams,String imgName){
        int stateInt = 1;
        if(instreams != null){
            try {
                File file=new File(imgName);//可以是任何图片格式.jpg,.png等
                FileOutputStream fos=new FileOutputStream(file);
                byte[] b = new byte[1024];
                int nRead = 0;
                while ((nRead = instreams.read(b)) != -1) {
                    fos.write(b, 0, nRead);
                }
                fos.flush();
                fos.close();
            } catch (Exception e) {
                stateInt = 0;
                e.printStackTrace();
            } finally {
            }
        }
        return stateInt;
    }
//    public static byte[] readInputStream(InputStream inStream) throws Exception{
//        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//        //创建一个Buffer字符串
//        byte[] buffer = new byte[1024];
//        //每次读取的字符串长度，如果为-1，代表全部读取完毕
//        int len = 0;
//        //使用一个输入流从buffer里把数据读取出来
//        while( (len=inStream.read(buffer)) != -1 ){
//        //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
//            outStream.write(buffer, 0, len);
//        }
//        //关闭输入流
//        inStream.close();
//        //把outStream里的数据写入内存
//        return outStream.toByteArray();
//    }
}
