package com.ssh.greenthumb.api.controller;

import com.ssh.greenthumb.api.dto.post.CommentDTO;
import com.ssh.greenthumb.api.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/post/{id}/comment")
    public Long add(@PathVariable Long id, @RequestBody CommentDTO.Create dto) {
        return commentService.add(id, dto);
    }

    @GetMapping("/post/{id}/comments")
    public List<CommentDTO.Get> getAllByPost(@PathVariable Long id) {
        return commentService.getAllByPost(id);
    }

    @GetMapping("user/{userId}/post/{postId}/comments")
    public List<CommentDTO.Get> getAllByUser(@PathVariable Long userId, @PathVariable Long postId) {
        return commentService.getAllByUser(postId, userId);
    }

    @PutMapping("/post/{postId}/comment/{commentId}")
    public Long update(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody CommentDTO.Update dto) {
        return commentService.update(commentId, dto);
    }

    @DeleteMapping("/post/{postId}/comment/{commentId}")
    public String delete(@PathVariable Long postId, @PathVariable Long commentId) {
        return commentService.delete(commentId);
    }

}