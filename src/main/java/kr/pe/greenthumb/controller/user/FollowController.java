package kr.pe.greenthumb.controller.user;

import kr.pe.greenthumb.dto.user.FollowDTO;
import kr.pe.greenthumb.service.user.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FollowController {

    private FollowService followService;

    // 팔로우 요청
    @PostMapping("/follower/{followerId}/following/{followingId}/follow")
    public Long add(@RequestBody FollowDTO.Create dto) {
        return followService.add(dto);
    }

    // 유저 한명의 팔로워 목록 조회
    @GetMapping("/user/{userId}/followers")
    public List<FollowDTO.Get> getFollowers(@RequestBody FollowDTO.Get dto) {
        return followService.getFollwers(dto);
    }

    // 유저 한명의 팔로잉 목록 조회
    @GetMapping("/user/{userId}/followings")
    public List<FollowDTO.Get> getFollowings(@RequestBody FollowDTO.Get dto) {
        return followService.getFollowings(dto);
    }

    // 언팔로우
    @DeleteMapping("/follow/{followerId}/user/{followingId}")
    public void delete(@RequestBody FollowDTO.Delete dto) {
         followService.delete(dto);
    }

}