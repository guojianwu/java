package gjw.cn.mybatis01.dao;

import gjw.cn.mybatis01.domain.Dept;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptDao {
    Dept getDeptById(Integer id);

    Dept getDeptByIdPlus(Integer id);

}
