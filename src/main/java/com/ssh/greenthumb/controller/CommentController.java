package com.ssh.greenthumb.controller;

import com.ssh.greenthumb.dto.post.CommentDTO;
import com.ssh.greenthumb.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/comment")
@RestController
public class CommentController {

    private final CommentService commentService;

    // 댓글 생성
    @PostMapping
    public Long add(@RequestBody CommentDTO.Create dto) {
        return commentService.add(dto);
    }

    // 게시글별 댓글 조회
    @GetMapping("/post/{postId}/comments")
    public List<CommentDTO.Get> getAllByPost(@PathVariable Long postId) {
        return commentService.getAllByPost(postId);
    }

    // 유저별 댓글 조회
    @GetMapping("/post/{postId}/user/{userId}/comments")
    public List<CommentDTO.Get> getAllByUser(@PathVariable Long postId, @PathVariable Long userId) {
        return commentService.getAllByUser(postId, userId);
    }

    // 댓글 수정
    @PutMapping("/{commentId}")
    public Long update(@PathVariable Long commentId, @RequestBody CommentDTO.Update dto) {
        return commentService.update(commentId, dto);
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public String delete(@PathVariable Long commentId) {
        return commentService.delete(commentId);
    }

}