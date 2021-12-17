package com.ssh.greenthumb.api.controller;

import com.ssh.greenthumb.api.dto.post.PostDTO;
import com.ssh.greenthumb.api.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping("/user/{id}/post")
    public Long add(@PathVariable Long id, @RequestBody PostDTO.Create dto) {
        return postService.add(id, dto);
    }

    @GetMapping("/posts")
    public List<PostDTO.Get> getAll() {
        return postService.getAll();
    }

    @GetMapping("/posts/category/{category}")
    public List<PostDTO.Get> getAllByCategory(@PathVariable String category) {
        return postService.getAllByCategory(category);
    }

    @GetMapping("/post/{id}")
    public PostDTO.Get getOne(@PathVariable Long id) {
        return postService.getOne(id);
    }

    @PutMapping("/post/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostDTO.Update dto) {
        return postService.update(id, dto);
    }

    @PutMapping("/post/{id}/check")
    public Long updateCheck(@PathVariable Long id) {
        return postService.updateCheck(id);
    }

    @DeleteMapping("/post/{id}")
    public void delete(@PathVariable Long id) {
        postService.delete(id);
    }

}