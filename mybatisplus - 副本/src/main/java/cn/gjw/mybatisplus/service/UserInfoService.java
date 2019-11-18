package cn.gjw.mybatisplus.service;

import cn.gjw.mybatisplus.entity.UserInfoEntity;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public interface UserInfoService extends IService<UserInfoEntity> {
    public IPage<UserInfoEntity> selectUserInfoByGtFraction(IPage<UserInfoEntity> page, Long fraction);
}
