package cn.gjw.mybatisplus.service.serviceImpl;

import cn.gjw.mybatisplus.dao.UserInfoDao;
import cn.gjw.mybatisplus.entity.UserInfoEntity;
import cn.gjw.mybatisplus.service.UserInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserInfoSerivceImpl extends ServiceImpl<UserInfoDao, UserInfoEntity> implements UserInfoService {
    @Autowired
    UserInfoDao userInfoDao;

    @Override
    public IPage<UserInfoEntity> selectUserInfoByGtFraction(IPage<UserInfoEntity> page, Long fraction) {
        return userInfoDao.selectUserInfoByGtFraction(page, fraction);
    }
}
