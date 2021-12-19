package com.ssh.greenthumb.api.controller;

import com.ssh.greenthumb.api.dto.post.CommentDTO;
import com.ssh.greenthumb.api.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Community", description = "게시판 API")
@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 작성", description = "사용자 id로 내용을 입력하여 댓글 작성을 합니다.")
    @PostMapping("/post/{id}/comment")
    public Long add(@PathVariable Long id, @RequestBody CommentDTO.Create dto) {
        return commentService.add(id, dto);
    }

    @Operation(summary = "게시글별 댓글 조회", description = "게시글 id로 해당 게시글의 모든 댓글을 조회합니다.")
    @GetMapping("/post/{id}/comments")
    public List<CommentDTO.Get> getAllByPost(@PathVariable Long id) {
        return commentService.getAllByPost(id);
    }

    @Operation(summary = "작성자별 댓글 조회", description = "사용자 id와 게시글 id로 사용자 한명의 모든 댓글을 조회합니다.")
    @GetMapping("user/{userId}/post/{postId}/comments")
    public List<CommentDTO.Get> getAllByUser(@PathVariable Long userId, @PathVariable Long postId) {
        return commentService.getAllByUser(postId, userId);
    }

    @Operation(summary = "댓글 수정", description = "게시글 id와 댓글 id로 댓글 내용을 수정합니다.")
    @PutMapping("/post/{postId}/comment/{commentId}")
    public Long update(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody CommentDTO.Update dto) {
        return commentService.update(commentId, dto);
    }

    @Operation(summary = "댓글 삭제", description = "사용자 id로 내용을 입력하여 댓글 작성을 합니다.")
    @DeleteMapping("/post/{postId}/comment/{commentId}")
    public String delete(@PathVariable Long postId, @PathVariable Long commentId) {
        return commentService.delete(commentId);
    }

    @Operation(summary = "댓글 좋아요 등록", description = "댓글 id와 사용자 id로 내용을 입력하여 댓글에 좋아요를 등록 합니다.")
    @PostMapping("/comment/{commentId}/user/{userId}/like")
    public Long likeComment(@PathVariable Long commentId, @PathVariable Long userId) {
        return commentService.likeComment(commentId, userId);
    }

    @Operation(summary = "댓글 좋아요 취소", description = "댓글 id와 사용자 id로 내용을 입력하여 댓글에 좋아요를 취소 합니다.")
    @DeleteMapping("/comment/{commentId}/user/{userId}/like")
    public void unLikeComment(@PathVariable Long commentId, @PathVariable Long userId) {
        commentService.unLikeComment(commentId, userId);
    }

}