package cn.gjw.mybatisplus.dao;

import cn.gjw.mybatisplus.entity.User2;
import cn.gjw.mybatisplus.entity.User2Example;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface User2Mapper {
    int countByExample(User2Example example);

    int deleteByExample(User2Example example);

    int deleteByPrimaryKey(Integer id);

    int insert(User2 record);

    int insertSelective(User2 record);

    List<User2> selectByExample(User2Example example);

    User2 selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User2 record, @Param("example") User2Example example);

    int updateByExample(@Param("record") User2 record, @Param("example") User2Example example);

    int updateByPrimaryKeySelective(User2 record);

    int updateByPrimaryKey(User2 record);
}