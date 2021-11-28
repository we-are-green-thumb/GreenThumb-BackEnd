package kr.pe.greenthumb.repository;

import kr.pe.greenthumb.domain.UserTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserTest, Long> {
    Optional<UserTest> findByEmail(String email);
}
