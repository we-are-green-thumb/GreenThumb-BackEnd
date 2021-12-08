package kr.pe.greenthumb.controller;

import kr.pe.greenthumb.dto.user.FollowDTO;
import kr.pe.greenthumb.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FollowController {

    private final FollowService followService;

    // 팔로우 요청
    @PostMapping("/follower/{followerId}/followee/{followeeId}/follow")
    public Long add(@RequestBody FollowDTO.Create dto) {
        return followService.add(dto);
    }

    // 유저 한명의 팔로워 목록 조회
    @GetMapping("/user/{userId}/followers")
    public List<FollowDTO.Get> getAllFollowers(@PathVariable Long userId) {
        return followService.getFollwers(userId);
    }

    // 유저 한명의 팔로잉 목록 조회
    @GetMapping("/user/{userId}/followees")
    public List<FollowDTO.Get> getAllFollowees(@PathVariable  Long userId) {
        return followService.getFollowees(userId);
    }

    // 언팔로우
    @DeleteMapping("/follow/{followerId}/followee/{followeeId}")
    public void delete(@RequestBody FollowDTO.Delete dto) {
         followService.delete(dto);
    }

}