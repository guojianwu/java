package cn.gjw.myjpa.repository;

import cn.gjw.myjpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
