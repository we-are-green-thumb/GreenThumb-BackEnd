package com.ssh.greenthumb.dao.user;

import com.ssh.greenthumb.domain.user.Follow;
import com.ssh.greenthumb.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    List<Follow> findFolloweeByfollower(User follower);

    List<Follow> findFollowerByfollowee(User followee);

    void deleteByFollowerAndFollowee(User follower, User followee);

}