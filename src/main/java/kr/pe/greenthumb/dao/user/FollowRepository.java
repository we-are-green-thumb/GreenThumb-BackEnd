package kr.pe.greenthumb.dao.user;

import kr.pe.greenthumb.domain.user.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {

}