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
    @PostMapping("/follow")
    public Long add(@RequestBody FollowDTO.Create dto) {
        return followService.add(dto);
    }

    // 유저 한명의 팔로워 목록 조회
    @GetMapping("/followee/{followeeId}/followers")
    public List<String> getAllFollowers(@PathVariable Long followeeId) {
        return followService.getFollwers(followeeId);
    }

    // 유저 한명의 팔로잉 목록 조회
    @GetMapping("/follower/{followerId}/following")
    public List<String> getAllFollowees(@PathVariable Long followerId) {
        return followService.getFollowees(followerId);
    }

    // 언팔로우
    @DeleteMapping("/follow")
    public void delete(@RequestBody FollowDTO.Delete dto) {
         followService.delete(dto);
    }

}