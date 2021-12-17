package com.ssh.greenthumb.api.dao.user;

import com.ssh.greenthumb.api.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAndIsDeleted(String email, String isDeleted);

    List<User> findAllByIsDeleted(String isDeleted);

    boolean existsByEmail(String userName);

    Boolean existsByNickName(String nickName);

    User findByProviderId(String provideId);
}