package com.ssh.greenthumb.auth.repository;

import com.ssh.greenthumb.api.domain.user.User;
import com.ssh.greenthumb.auth.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    RefreshToken findByUser(User user);

    void deleteByUser(User user);

}
