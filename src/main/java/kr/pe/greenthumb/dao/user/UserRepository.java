package kr.pe.greenthumb.dao.user;

import kr.pe.greenthumb.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByIsDeleted(String isDeleted);

}
