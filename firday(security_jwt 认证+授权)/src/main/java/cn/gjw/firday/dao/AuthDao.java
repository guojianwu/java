package cn.gjw.firday.dao;

import cn.gjw.firday.domain.auth.Auth;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthDao {
    Auth getUserByUsername(String name);
    Auth getUserById(Long id);
}
