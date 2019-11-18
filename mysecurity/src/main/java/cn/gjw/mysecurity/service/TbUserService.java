package cn.gjw.mysecurity.service;

import cn.gjw.mysecurity.dao.TbUserDao;
import cn.gjw.mysecurity.domin.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbUserService {
    @Autowired
    TbUserDao tbUserDao;

    public TbUser getUserByName(String name){
        return tbUserDao.getUserByName(name);
    }
}
