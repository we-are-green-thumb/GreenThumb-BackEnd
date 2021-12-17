package com.ssh.greenthumb.api.service;

import com.ssh.greenthumb.api.common.exception.NotFoundException;
import com.ssh.greenthumb.api.dao.user.FollowRepository;
import com.ssh.greenthumb.api.dao.user.UserRepository;
import com.ssh.greenthumb.api.domain.user.Follow;
import com.ssh.greenthumb.api.domain.user.User;
import com.ssh.greenthumb.api.dto.user.FollowDTO;
import com.ssh.greenthumb.auth.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FollowService {

    private final FollowRepository followDao;
    private final UserRepository userDao;

    @Transactional
    public String add(Long userId, Long followeeId) {
        User follower = userDao.findById(userId).
                orElseThrow(NotFoundException::new);

        User following = userDao.findById(followeeId).
                orElseThrow(NotFoundException::new);

        if(following.getIsBlack().equals("n") && following.getIsDeleted().equals("n")) {
            followDao.save(FollowDTO.toEntity(follower, following)).getId();

            return "팔로우 요청 완료";
        } else return "해당 회원은 요청 불가";
    }

    @Transactional
    public List<FollowDTO.Follower> getFollwers(Long id) {

        User followee = userDao.findById(id).
                orElseThrow(NotFoundException::new);

        List<Follow> followerList = followDao.findFollowerByfollowee(followee);

        for(Follow f : followerList) {
            if(f.getFollower().getRole() == Role.BLACK) followDao.delete(f);
        }

        return followerList.stream().map(FollowDTO.Follower::new).collect(Collectors.toList());
    }

    @Transactional
    public List<FollowDTO.Followee> getFollowees(Long id) {

        User follower = userDao.findById(id).
                orElseThrow(NotFoundException::new);

        List<Follow> followeeList = followDao.findFolloweeByfollower(follower);

        for(Follow f : followeeList) {
            if(f.getFollowee().getIsBlack().equals("y")) followDao.delete(f);
        }

        return followeeList.stream().map(FollowDTO.Followee::new).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long userId, Long followeeId) {
        User follower = userDao.findById(userId).
                orElseThrow(NotFoundException::new);

        User followee = userDao.findById(followeeId).
                orElseThrow(NotFoundException::new);

        followDao.deleteByFollowerAndFollowee(follower, followee);
    }

}