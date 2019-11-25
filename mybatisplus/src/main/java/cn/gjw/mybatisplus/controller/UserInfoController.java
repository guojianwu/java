package cn.gjw.mybatisplus.controller;

import cn.gjw.mybatisplus.entity.UserInfoEntity;
import cn.gjw.mybatisplus.service.UserInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/getInfo")
    public UserInfoEntity getInfo(String userId) {
        UserInfoEntity userInfoEntity = userInfoService.getById(userId);
        return userInfoEntity;
    }

    /**
     * 分页查询全部数据
     *
     * @Author Sans
     * @CreateTime 2019/6/8 16:37
     * @Return IPage<UserInfoEntity> 分页数据
     */
    @RequestMapping("/getInfoListPage")
    public IPage<UserInfoEntity> getInfoListPage(@Param("pageNum") Long pageNum, @Param("pageSize") Long pageSize) {
        System.out.println(pageNum);
        System.out.println(pageSize);
        Page<UserInfoEntity> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        IPage<UserInfoEntity> page1 = userInfoService.page(page);
        return page1;
    }

    /**
     * 新增用户信息
     *
     * @Author Sans
     * @CreateTime 2019/6/8 16:40
     */
    @RequestMapping("/saveInfo")
    public Long saveInfo() {
        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setName("小龙3");
        userInfoEntity.setSkill("JAVA");
        userInfoEntity.setAge(18);
        userInfoEntity.setFraction(59L);
        userInfoEntity.setEvaluate("该学生是一个在改BUG的码农");

        userInfoService.save(userInfoEntity);
        System.out.println(userInfoEntity);

        System.out.println(userInfoEntity.getId());
        return userInfoEntity.getId();
    }

    @RequestMapping("/saveInfoList")
    public String saveInfoList() {
        UserInfoEntity sans = new UserInfoEntity();
        sans.setName("Sans");
        sans.setSkill("睡觉");
        sans.setAge(18);
        sans.setFraction(60L);
        sans.setEvaluate("Sans是一个爱睡觉,并且身材较矮骨骼巨大的骷髅小胖子");

        UserInfoEntity papyrus = new UserInfoEntity();
        papyrus.setName("papyrus");
        papyrus.setSkill("JAVA");
        papyrus.setAge(18);
        papyrus.setFraction(58L);
        papyrus.setEvaluate("Papyrus是一个讲话大声、个性张扬的骷髅，给人自信、有魅力的骷髅小瘦子");

        ArrayList<UserInfoEntity> list = new ArrayList<>();
        list.add(sans);
        list.add(papyrus);

        userInfoService.saveBatch(list);
        return "新增成功";

    }

    /**
     * MP自定义SQL
     * @Author Sans
     * @CreateTime 2019/6/9 14:37
     * @Return IPage<UserInfoEntity> 分页数据
     */
//    @RequestMapping("/getInfoListSQL")
//    public IPage<UserInfoEntity> getInfoListSQL(@Param("pageNum") Long pageNum, @Param("pageSize") Long pageSize) {
//        IPage<UserInfoEntity> page = new Page<>();
//        page.setCurrent(pageNum);
//        page.setSize(pageSize);
//        page= userInfoService.selectUserInfoByGtFraction(page, 60L);
//        return page;
//    }
}
