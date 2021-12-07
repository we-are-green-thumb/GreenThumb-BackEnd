package kr.pe.greenthumb.controller.post;

import kr.pe.greenthumb.dto.post.PostDTO;
import kr.pe.greenthumb.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping("/user/{userId}")
    public Long add(@PathVariable Long userId, @RequestBody PostDTO.Create dto) {
        return postService.add(userId, dto);
    }

    @GetMapping("/post/{postCategory}")
    public List<PostDTO.Get> getAll(@PathVariable String postCategory) {
        return postService.getAll(postCategory);
    }

    @GetMapping("post/{postId}")
    public PostDTO.Get getOne(@PathVariable Long postId) {
        return postService.getOne(postId);
    }

    @PutMapping("post/{postId}")
    public Long update(@PathVariable Long postId, @RequestBody PostDTO.Update dto) {
        return postService.update(postId, dto);
    }

    @PutMapping("post/{postId}/check")
    public Long updateCheck(@PathVariable Long postId, @RequestBody PostDTO.UpdateCheck dto) {
        return postService.updateCheck(postId, dto);
    }

    @DeleteMapping("post/{postId}")
    public void delete(@PathVariable Long postId) {
        postService.delete(postId);
    }

}
