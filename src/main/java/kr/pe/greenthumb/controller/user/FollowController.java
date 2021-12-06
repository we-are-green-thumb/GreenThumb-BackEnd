package kr.pe.greenthumb.controller.user;

import kr.pe.greenthumb.dao.user.FollowRepository;
import kr.pe.greenthumb.dao.user.UserRepository;
import kr.pe.greenthumb.dto.user.FollowDTO;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FollowController {
    FollowController() {
        System.out.println("FollowController(){}");
    }

    private FollowRepository followDao;
    private UserRepository userDao;

    public void addFollow(FollowDTO.Create follow, Long followUser, Long followerUser) {
//        User followee = userDao.findUserById(followUser);
//        User follower = userDao.findUserById(followerUser);


    }

}
