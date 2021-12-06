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
    @PostMapping("/post/{postid}/user/{userid}/comment")
    public Long add(@PathVariable Long postid, @PathVariable Long userid, @RequestBody CommentDTO.Create dto) {
        return commentService.add(postid, userid, dto);
    }

    // 게시글별 댓글 조회
    @GetMapping("/post/{postid}/comments")
    public List<CommentDTO.Get> getAllByPost(@PathVariable Long postid) {
        return commentService.getAllByPost(postid);
    }

    // 유저별 댓글 조회
    @GetMapping("post/{postid}/user/{userid}/comments")
    public List<CommentDTO.Get> getAllByUser(@PathVariable Long postid, @PathVariable Long userid) {
        return commentService.getAllByUser(postid, userid);
    }

    // 댓글 수정
    @PutMapping("/post/{postid}/user/{userid}/comment/{commentid}")
    public Long update(@PathVariable Long postid, @PathVariable Long userid, @PathVariable Long commentid, @RequestBody CommentDTO.Update dto) {
        return commentService.update(postid, userid, commentid, dto);
    }

    // 댓글 삭제
    @DeleteMapping("/comment/{commentid}")
    public void delete(@PathVariable Long commentid) {
        commentService.delete(commentid);
    }

}
