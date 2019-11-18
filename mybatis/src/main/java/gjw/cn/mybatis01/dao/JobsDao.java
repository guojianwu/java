package gjw.cn.mybatis01.dao;

import gjw.cn.mybatis01.domain.Jobs;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface JobsDao {
    List<Jobs> allJobs();
}
