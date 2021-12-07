package kr.pe.greenthumb.dao.user;

import kr.pe.greenthumb.domain.user.Follow;
import kr.pe.greenthumb.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    List<Follow> findAllByFollower(User follower);

    List<Follow> findAllByFollowee(User followee);

    void deleteByFollowerAndFollowee(User follower, User followee);

}