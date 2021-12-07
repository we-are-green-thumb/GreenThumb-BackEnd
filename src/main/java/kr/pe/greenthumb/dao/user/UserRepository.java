package kr.pe.greenthumb.dao.user;

import kr.pe.greenthumb.domain.post.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
