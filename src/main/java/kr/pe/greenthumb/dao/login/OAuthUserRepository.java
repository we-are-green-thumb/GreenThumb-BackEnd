package kr.pe.greenthumb.dao.login;

import kr.pe.greenthumb.domain.login.OAuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OAuthUserRepository extends JpaRepository<OAuthUser, Long> {
    Optional<OAuthUser> findByEmail(String email);

    Boolean existsByEmail(String email);
}
