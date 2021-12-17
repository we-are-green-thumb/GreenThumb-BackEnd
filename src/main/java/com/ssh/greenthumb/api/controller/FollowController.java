package com.ssh.greenthumb.api.controller;

import com.ssh.greenthumb.api.dto.user.FollowDTO;
import com.ssh.greenthumb.api.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FollowController {

    private final FollowService followService;

    @PostMapping("/user/{userId}/followee/{followeeId}/follow")
    public String add(@PathVariable Long userId, @PathVariable Long followeeId) {
        return followService.add(userId, followeeId);
    }

    @GetMapping("/follow-user/{id}/followers")
    public List<FollowDTO.Follower> getFollwers(@PathVariable Long id) {
        return followService.getFollwers(id);
    }

    @GetMapping("/follow-user/{id}/followees")
    public List<FollowDTO.Followee> getFollowees(@PathVariable Long id) {
        return followService.getFollowees(id);
    }

    @DeleteMapping("/user/{userId}/followee/{followeeId}/follow")
    public void delete(@PathVariable Long userId, @PathVariable Long followeeId) {
         followService.delete(userId, followeeId);
    }

}