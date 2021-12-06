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
    @PostMapping("/post/{postid}/like")
    public Long likePost(@PathVariable Long postid, @PathVariable Long userid) {
        return likeService.likePost(postid, userid);
    }

    // 게시글 좋아요 취소
    @DeleteMapping("/post/{postid}/like")
    public void unLikePost(@PathVariable Long likePostid) {
        likeService.unLikePost(likePostid);
    }

    // 댓글 좋아요 등록
    @PostMapping("/comment/{commentid}/like")
    public Long likeComment(@PathVariable Long commentid, @PathVariable Long userid) {
        return likeService.likeComment(commentid, userid);
    }

    // 댓글 좋아요 취소
    @DeleteMapping("/comment/{commentid}/like")
    public void unLikeComment(@PathVariable Long likeCommentid) {
        likeService.unLikeComment(likeCommentid);
    }

}
