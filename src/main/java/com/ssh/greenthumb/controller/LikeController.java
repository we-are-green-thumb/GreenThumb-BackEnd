package com.ssh.greenthumb.controller;

import com.ssh.greenthumb.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LikeController {

    private final LikeService likeService;

    // 게시글 좋아요 등록
    @PostMapping("/post/{postId}/user/{userId}/like")
    public Long likePost(@PathVariable Long postId, @PathVariable Long userId) {
        return likeService.likePost(postId, userId);
    }

    // 게시글 좋아요 취소
    @DeleteMapping("/post/{postId}/user/{userId}/like")
    public void unLikePost(@PathVariable Long postId, @PathVariable Long userId) {
        likeService.unLikePost(postId, userId);
    }

    // 댓글 좋아요 등록
    @PostMapping("/comment/{commentId}/user/{userId}/like")
    public Long likeComment(@PathVariable Long commentId, @PathVariable Long userId) {
        return likeService.likeComment(commentId, userId);
    }

    // 댓글 좋아요 취소
    @DeleteMapping("/comment/{commentId}/user/{userId}/like")
    public void unLikeComment(@PathVariable Long commentId, @PathVariable Long userId) {
        likeService.unLikeComment(commentId, userId);
    }

}
