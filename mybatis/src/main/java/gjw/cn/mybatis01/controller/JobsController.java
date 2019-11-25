package gjw.cn.mybatis01.controller;

import gjw.cn.mybatis01.domain.Jobs;
import gjw.cn.mybatis01.service.JobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JobsController {

    @Autowired
    private JobsService jobsService;

    @GetMapping("/alljobs")
    public List<Jobs> allJobs() {
        return jobsService.allJobs();
    }


}
