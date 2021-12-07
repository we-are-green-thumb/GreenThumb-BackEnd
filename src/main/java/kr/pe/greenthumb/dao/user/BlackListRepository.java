package kr.pe.greenthumb.dao.user;

import kr.pe.greenthumb.domain.user.BlackList;
import kr.pe.greenthumb.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlackListRepository extends JpaRepository<BlackList, Long> {

    BlackList findByUserAndBlackStatus(User user, String blackStatus);

}