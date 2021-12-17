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

    // 팔로우 요청
    @Transactional
    public String add(FollowDTO.Create dto) {
        User follower = userDao.findById(dto.getFollowerId()).
                orElseThrow(NotFoundException::new);

        User following = userDao.findById(dto.getFolloweeId()).
                orElseThrow(NotFoundException::new);

        if(following.getIsBlack().equals("n") && following.getIsDeleted().equals("n")) {
            followDao.save(dto.toEntity(follower, following)).getId();

            return "팔로우 요청 완료";
        } else return "해당 회원은 요청 불가";
    }

    // 유저 한명의 팔로워 목록 조회
    @Transactional
    public List<FollowDTO.Follower> getFollwers(Long userId) {

        User followee = userDao.findById(userId).
                orElseThrow(NotFoundException::new);

        List<Follow> followerList = followDao.findFollowerByfollowee(followee);

        for(Follow f : followerList) {
            if(f.getFollower().getRole() == Role.BLACK) followDao.delete(f);
        }

        return followerList.stream().map(FollowDTO.Follower::new).collect(Collectors.toList());
    }

    // 유저 한명의 팔로잉 목록 조회
    @Transactional
    public List<FollowDTO.Followee> getFollowees(Long userId) {

        User follower = userDao.findById(userId).
                orElseThrow(NotFoundException::new);

        List<Follow> followeeList = followDao.findFolloweeByfollower(follower);

        for(Follow f : followeeList) {
            if(f.getFollowee().getIsBlack().equals("y")) followDao.delete(f);
        }

        return followeeList.stream().map(FollowDTO.Followee::new).collect(Collectors.toList());
    }

    // 언팔로우
    @Transactional
    public void delete(FollowDTO.Delete dto) {
        User follower = userDao.findById(dto.getFollowerId()).
                orElseThrow(NotFoundException::new);

        User followee = userDao.findById(dto.getFolloweeId()).
                orElseThrow(NotFoundException::new);

        followDao.deleteByFollowerAndFollowee(follower, followee);
    }

}