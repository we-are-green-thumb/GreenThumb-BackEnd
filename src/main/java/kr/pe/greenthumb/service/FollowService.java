package kr.pe.greenthumb.service;

import kr.pe.greenthumb.common.exception.NotFoundException;
import kr.pe.greenthumb.dao.user.FollowRepository;
import kr.pe.greenthumb.dao.user.UserRepository;
import kr.pe.greenthumb.domain.user.User;
import kr.pe.greenthumb.dto.user.FollowDTO;
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
    public Long add(FollowDTO.Create dto) {
        User follower = userDao.findById(dto.getFollowerId()).
                orElseThrow(NotFoundException::new);

        User following = userDao.findById(dto.getFolloweeId()).
                orElseThrow(NotFoundException::new);

        return followDao.save(dto.toEntity(follower, following)).getFollowId();
    }

    // 유저 한명의 팔로워 목록 조회
    @Transactional
    public List<FollowDTO.Get> getFollwers(FollowDTO.Get dto) {
        User user = userDao.findById(dto.getFolloweeId()).
                orElseThrow(NotFoundException::new);

        return followDao.findAllByFollowee(user).stream().map(FollowDTO.Get::new).collect(Collectors.toList());
    }

    // 유저 한명의 팔로잉 목록 조회
    @Transactional
    public List<FollowDTO.Get> getFollowees(FollowDTO.Get dto) {
        User user = userDao.findById(dto.getFollowerId()).
                orElseThrow(NotFoundException::new);

        return followDao.findAllByFollower(user).stream().map(FollowDTO.Get::new).collect(Collectors.toList());
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