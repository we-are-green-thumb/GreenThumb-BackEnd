package kr.pe.greenthumb.controller;

import kr.pe.greenthumb.service.LikeService;
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
    @PostMapping("/post/{postId}/like")
    public Long likePost(@PathVariable Long postId, @PathVariable Long userId) {
        return likeService.likePost(postId, userId);
    }

    // 게시글 좋아요 취소
    @DeleteMapping("/post/{postId}/like")
    public void unLikePost(@PathVariable Long likePostId) {
        likeService.unLikePost(likePostId);
    }

    // 댓글 좋아요 등록
    @PostMapping("/comment/{commentId}/like")
    public Long likeComment(@PathVariable Long commentId, @PathVariable Long userId) {
        return likeService.likeComment(commentId, userId);
    }

    // 댓글 좋아요 취소
    @DeleteMapping("/comment/{commentId}/like")
    public void unLikeComment(@PathVariable Long likeCommentId) {
        likeService.unLikeComment(likeCommentId);
    }

}
