package cn.gjw.mybatiscache.controller;

import cn.gjw.mybatiscache.domain.User;
import cn.gjw.mybatiscache.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable("id") Integer id) {
        User user = userService.getUserById(id);
        return user;
    }


    @PostMapping(value = "/upload")
    public String a(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        System.out.println(multipartFile.getOriginalFilename());

        File file = null;
        InputStream ins = null;
        try {
            ins = multipartFile.getInputStream();
            file = new File("src/main/resources/static/imgs/" + multipartFile.getOriginalFilename());
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ss";
    }

}
