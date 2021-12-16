package com.ssh.greenthumb.api.dao.login;

import com.ssh.greenthumb.api.domain.login.UserRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshToken, Long> {

    UserRefreshToken findByUserId(Long userId);

    UserRefreshToken findByUserIdAndRefreshToken(Long userId, String refreshToken);

}
