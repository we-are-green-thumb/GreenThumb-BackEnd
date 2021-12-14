package com.ssh.greenthumb.controller;

import com.ssh.greenthumb.dto.user.FollowDTO;
import com.ssh.greenthumb.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/follow")
@RestController
public class FollowController {

    private final FollowService followService;

    // 팔로우 요청
    @PostMapping
    public String add(@RequestBody FollowDTO.Create dto) {
        return followService.add(dto);
    }

    // 유저 한명의 팔로워 목록 조회
    @GetMapping("/followee/{userId}/followers")
    public List<String> getAllFollowers(@PathVariable Long userId) {
        return followService.getFollwers(userId);
    }

    // 유저 한명의 팔로잉 목록 조회
    @GetMapping("/follower/{userId}/following")
    public List<String> getAllFollowees(@PathVariable Long userId) {
        return followService.getFollowees(userId);
    }

    @GetMapping("/followersCount/{userId}")
    public int getFollwersCount(@PathVariable Long userId) {
        return followService.getFollwersCount(userId);
    }

    @GetMapping("followeesCount/{userId}")
    public int getFollweesCount(@PathVariable Long userId) {
        return followService.getFollweesCount(userId);
    }


    // 언팔로우
    @DeleteMapping
    public void delete(@RequestBody FollowDTO.Delete dto) {
         followService.delete(dto);
    }

}