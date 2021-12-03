package kr.pe.greenthumb.dao.login;

import kr.pe.greenthumb.domain.login.OAuth2User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OAuth2UserRepository extends JpaRepository<OAuth2User, Long> {
    Optional<OAuth2User> findByEmail(String email);
}
