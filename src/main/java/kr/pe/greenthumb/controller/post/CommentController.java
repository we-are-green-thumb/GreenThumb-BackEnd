package kr.pe.greenthumb.controller.post;

import kr.pe.greenthumb.dto.post.CommentDTO;
import kr.pe.greenthumb.service.post.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    // 댓글 생성
    @PostMapping("/post/{postId}/user/{userId}/comment")
    public Long add(@PathVariable Long postId, @PathVariable Long userId, @RequestBody CommentDTO.Create dto) {
        return commentService.add(postId, userId, dto);
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
    @PutMapping("/comment/{commentId}")
    public Long update(@PathVariable Long commentId, @RequestBody CommentDTO.Update dto) {
        return commentService.update(commentId, dto);
    }

    // 댓글 삭제
    @DeleteMapping("/comment/{commentId}")
    public void delete(@PathVariable Long commentId) {
        commentService.delete(commentId);
    }

}
