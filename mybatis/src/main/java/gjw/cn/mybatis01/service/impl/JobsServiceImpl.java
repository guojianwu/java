package gjw.cn.mybatis01.service.impl;

import gjw.cn.mybatis01.dao.JobsDao;
import gjw.cn.mybatis01.domain.Jobs;
import gjw.cn.mybatis01.service.JobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobsServiceImpl implements JobsService {

    @Autowired
    private JobsDao jobsDao;

    public List<Jobs> allJobs() {
        return jobsDao.allJobs();
    }
}
