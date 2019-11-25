package cn.gjw.mysecurity.service;

import cn.gjw.mysecurity.dao.TbPermissionDao;
import cn.gjw.mysecurity.domin.TbPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbPermissionService {
    @Autowired
    TbPermissionDao tbPermissionDao;

    public List<TbPermission> getUserById(Integer id) {
        return tbPermissionDao.getUserById(id);
    }
}
