package kr.pe.greenthumb.service;

import kr.pe.greenthumb.common.exception.NotFoundException;
import kr.pe.greenthumb.dao.user.FollowRepository;
import kr.pe.greenthumb.dao.user.UserRepository;
import kr.pe.greenthumb.domain.user.Follow;
import kr.pe.greenthumb.domain.user.User;
import kr.pe.greenthumb.dto.user.FollowDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FollowService {

    private final FollowRepository followDao;
    private final UserRepository userDao;

    // 팔로우 요청
    @Transactional
    public Long add(FollowDTO.Create dto) {
        User follower = userDao.findById(dto.getFollowerId()).
                orElseThrow(NotFoundException::new);

        User following = userDao.findById(dto.getFolloweeId()).
                orElseThrow(NotFoundException::new);

        return followDao.save(dto.toEntity(follower, following)).getFollowId();
    }

    // 유저 한명의 팔로워 목록 조회
    @Transactional
    public List<String> getFollwers(Long followeeId) {
        User followee = userDao.findById(followeeId).
                orElseThrow(NotFoundException::new);

        List<Follow> followList = followDao.findAllByFollowee(followee);
        List<String> nickNameList = new ArrayList<>();

        for(Follow f : followList) {
            nickNameList.add(f.getFollower().getUserNickname());
        }

        return nickNameList;
    }

    // 유저 한명의 팔로잉 목록 조회
    @Transactional
    public List<String> getFollowees(Long followerId) {
        User follower = userDao.findById(followerId).
                orElseThrow(NotFoundException::new);

        List<Follow> followList = followDao.findAllByFollowee(follower);
        List<String> nickNameList = new ArrayList<>();

        for(Follow f : followList) {
            nickNameList.add(f.getFollower().getUserNickname());
        }

        return nickNameList;
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