package cn.gjw.mybatisplus.dao;

import cn.gjw.mybatisplus.entity.UserInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoDao extends BaseMapper<UserInfoEntity> {
    IPage<UserInfoEntity> selectUserInfoByGtFraction(IPage<UserInfoEntity> page, Long fraction);
}
