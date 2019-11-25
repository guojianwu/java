package cn.gjw.mybatisplus2.scheduling;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
public class TestTask {

    //    @Scheduled(fixedRate = 2000)
    public void test() {
        System.out.println(System.currentTimeMillis());
    }
}
