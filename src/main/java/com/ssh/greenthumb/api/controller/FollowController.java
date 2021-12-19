package com.ssh.greenthumb.api.controller;

import com.ssh.greenthumb.api.dto.user.FollowDTO;
import com.ssh.greenthumb.api.service.FollowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User", description = "사용자 API")
@RequiredArgsConstructor
@RestController
public class FollowController {

    private final FollowService followService;

    @Operation(summary = "팔로우 요청", description = "팔로워의 id와 팔로우할 사용자의 id를 이용해 팔로우 요청을 합니다.")
    @PostMapping("/user/{userId}/followee/{followeeId}/follow")
    public String add(@PathVariable Long userId, @PathVariable Long followeeId) {
        return followService.add(userId, followeeId);
    }

    @Operation(summary = "팔로워 조회", description = "사용자의 id로 팔로워 목록을 조회합니다.")
    @GetMapping("/follow-user/{id}/followers")
    public List<FollowDTO.Follower> getFollwers(@PathVariable Long id) {
        return followService.getFollwers(id);
    }

    @Operation(summary = "팔로잉 조회", description = "사용자의 id로 팔로잉 목록을 조회합니다.")
    @GetMapping("/follow-user/{id}/followees")
    public List<FollowDTO.Followee> getFollowees(@PathVariable Long id) {
        return followService.getFollowees(id);
    }

    @Operation(summary = "팔로우 취소", description = "팔로워의 id와 팔로우할 사용자의 id를 이용해 팔로우를 취소 합니다.")
    @DeleteMapping("/user/{userId}/followee/{followeeId}/follow")
    public void delete(@PathVariable Long userId, @PathVariable Long followeeId) {
         followService.delete(userId, followeeId);
    }

}