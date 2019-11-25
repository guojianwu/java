package cn.gjw.mybatisnx;

import cn.gjw.mybatisnx.dao.UserInfoMapper;
import cn.gjw.mybatisnx.entity.UserInfo;
import cn.gjw.mybatisnx.entity.UserInfoExample;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisnxApplicationTests {
    @Autowired
    UserInfoMapper userInfoMapper;

    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void logg() {
        logger.trace("日志：trace");
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
    }

    @Test
    void contextLoads() {
//        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(1L);
//        System.out.println(userInfo);
        UserInfoExample userInfoExample = new UserInfoExample();
        List<UserInfo> userInfos = userInfoMapper.selectByExample(userInfoExample);
        System.out.println(userInfos);
    }

}
