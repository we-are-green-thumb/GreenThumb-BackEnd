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
    @PostMapping("/post/{postIdx}/like")
    public Long likePost(@PathVariable Long postIdx, @PathVariable Long userIdx) {
        return likeService.likePost(postIdx, userIdx);
    }

    // 게시글 좋아요 취소
    @DeleteMapping("/post/{postIdx}/like")
    public void unLikePost(@PathVariable Long likePostIdx) {
        likeService.unLikePost(likePostIdx);
    }

    // 댓글 좋아요 등록
    @PostMapping("/comment/{commentIdx}/like")
    public Long likeComment(@PathVariable Long commentIdx, @PathVariable Long userIdx) {
        return likeService.likeComment(commentIdx, userIdx);
    }

    // 댓글 좋아요 취소
    @DeleteMapping("/comment/{commentIdx}/like")
    public void unLikeComment(@PathVariable Long likeCommentIdx) {
        likeService.unLikeComment(likeCommentIdx);
    }

}
