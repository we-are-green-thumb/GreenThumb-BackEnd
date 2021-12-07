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

    @PostMapping("/post/{postId}")
    public Post add(@RequestBody PostDTO.Create dto) {
        return postService.add(dto);
    }

    @GetMapping
    public List<Post> getAll(String postCategory) {
        return postService.getAll(postCategory);
    }

    @GetMapping
    public Post getOne(Long postId) {
        return postService.getOne(postId);
    }

    @PutMapping
    public Long update(PostDTO.Update dto) {
        return postService.update(dto);
    }

    @DeleteMapping
    public void delete(Long postId) {
        postService.delete(postId);
    }

}
