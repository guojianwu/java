package cn.gjw.mysecurity.dao;

import cn.gjw.mysecurity.domin.TbPermission;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TbPermissionDao {
    List<TbPermission> getUserById(Integer id);
}
