package com.ssh.greenthumb.api.controller;

import com.ssh.greenthumb.api.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/post/{postId}/user/{userId}/like")
    public String likePost(@PathVariable Long postId, @PathVariable Long userId) {
        return likeService.likePost(postId, userId);
    }

    @PostMapping("/comment/{commentId}/user/{userId}/like")
    public Long likeComment(@PathVariable Long commentId, @PathVariable Long userId) {
        return likeService.likeComment(commentId, userId);
    }

    @DeleteMapping("/comment/{commentId}/user/{userId}/like")
    public void unLikeComment(@PathVariable Long commentId, @PathVariable Long userId) {
        likeService.unLikeComment(commentId, userId);
    }

}
