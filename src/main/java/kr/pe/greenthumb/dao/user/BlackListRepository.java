package kr.pe.greenthumb.dao.user;

import kr.pe.greenthumb.domain.user.BlackList;
import kr.pe.greenthumb.domain.post.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackListRepository extends JpaRepository<BlackList, Long> {

    BlackList findByUser(User user);

}