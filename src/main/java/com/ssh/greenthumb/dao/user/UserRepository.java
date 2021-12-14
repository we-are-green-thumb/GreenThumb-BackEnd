package com.ssh.greenthumb.dao.user;

import com.ssh.greenthumb.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByEmailAndIsDeleted(String email, String isDeleted);

    List<User> findAllByIsDeleted(String isDeleted);

    Boolean existsByEmail(String userName);

}
