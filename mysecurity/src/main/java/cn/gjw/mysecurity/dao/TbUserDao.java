package cn.gjw.mysecurity.dao;

import cn.gjw.mysecurity.domin.TbUser;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface TbUserDao {
   public TbUser getUserByName(String name);

}
