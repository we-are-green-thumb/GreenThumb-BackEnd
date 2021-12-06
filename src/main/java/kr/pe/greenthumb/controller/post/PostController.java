package kr.pe.greenthumb.controller.post;

import kr.pe.greenthumb.domain.post.Post;
import kr.pe.greenthumb.dto.post.PostDTO;
import kr.pe.greenthumb.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    // 게시글 생성
    @PostMapping("/post/{postId}")
    public Post add(@RequestBody PostDTO.Create dto) {
        return postService.add(dto);
    }

    // 게시글 조회
    @GetMapping("/post/{postId}")
    public List<Post> getAll(String postCategory) {
        return postService.getAll(postCategory);
    }

    //게시글 수정
    @PutMapping("/post/{postId}")
    public void update(Post post) {
        postService.update(post);
    }

    // 게시글 삭제
    @DeleteMapping("/post/{postId}")
    public void delete(Long postId) {
        postService.delete(postId);
    }

}
