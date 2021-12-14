package com.ssh.greenthumb.controller;

import com.ssh.greenthumb.dto.post.FileDTO;
import com.ssh.greenthumb.dto.post.PostDTO;
import com.ssh.greenthumb.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/post")
@RestController
public class PostController {

    private final PostService postService;

    //Q 카테고리명 어떻게 할지!
    @PostMapping
    public Long add(@RequestBody PostDTO.Create dto) {
        return postService.add(dto);
    }

    @GetMapping("/posts/{postCategory}")
    public List<PostDTO.Get> getAll(@PathVariable String postCategory) {
        return postService.getAll(postCategory);
    }

    @GetMapping("/{postId}")
    public PostDTO.Get getOne(@PathVariable Long postId) {
        return postService.getOne(postId);
    }

    @PutMapping("/{postId}")
    public Long update(@PathVariable Long postId, @RequestBody PostDTO.Update dto) {
        return postService.update(postId, dto);
    }

    @PatchMapping("/{postId}/check")
    public Long updateCheck(@PathVariable Long postId) {
        return postService.updateCheck(postId);
    }

    @DeleteMapping("/{postId}")
    public void delete(@PathVariable Long postId) {
        postService.delete(postId);
    }

    @PostMapping("/{postId}/file")
    public Long addFile(@PathVariable Long postId, @RequestBody FileDTO.Create dto) {
        return postService.addFile(postId, dto);
    }

    @DeleteMapping("/file/{fileId}")
    public void deleteFile(@PathVariable Long fileId) {
        postService.deleteFile(fileId);
    }

}