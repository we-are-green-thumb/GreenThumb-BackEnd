package kr.pe.greenthumb.controller.post;

import kr.pe.greenthumb.dto.post.CommentDTO;
import kr.pe.greenthumb.service.post.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    // 저장
    @PostMapping("/post/{postId}/comment")
    public Long save(@PathVariable Long postId, @RequestBody CommentDTO.Create dto) {
        return commentService.save(postId, dto);
    }
}
