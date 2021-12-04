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
    @PostMapping("/post/{postIdx}/user/{userIdx}/comment")
    public Long add(@PathVariable Long postIdx, @PathVariable Long userIdx, @RequestBody CommentDTO.Create dto) {
        return commentService.add(postIdx, userIdx, dto);
    }

    // 게시글별 댓글 조회
    @GetMapping("/post/{postIdx}/comments")
    public List<CommentDTO.Get> getAllByPost(@PathVariable Long postIdx) {
        return commentService.getAllByPost(postIdx);
    }

    // 유저별 댓글 조회
    @GetMapping("post/{postIdx}/user/{userIdx}/comments")
    public List<CommentDTO.Get> getAllByUser(@PathVariable Long postIdx, @PathVariable Long userIdx) {
        return commentService.getAllByUser(postIdx, userIdx);
    }

    // 댓글 수정
    @PutMapping("/post/{postIdx}/user/{userIdx}/comment/{commentIdx}")
    public Long update(@PathVariable Long postIdx, @PathVariable Long userIdx, @PathVariable Long commentIdx, @RequestBody CommentDTO.Update dto) {
        return commentService.update(postIdx, userIdx, commentIdx, dto);
    }

    // 댓글 삭제
    @DeleteMapping("/comment/{commentIdx}")
    public void delete(@PathVariable Long commentIdx) {
        commentService.delete(commentIdx);
    }

}
