package kr.pe.greenthumb.controller;

import kr.pe.greenthumb.dto.post.FileDTO;
import kr.pe.greenthumb.dto.post.PostDTO;
import kr.pe.greenthumb.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping("/post/user/{userId}")
    public Long add(@RequestBody PostDTO.Create dto) {
        return postService.add(dto);
    }

    @GetMapping("/post/{postCategory}")
    public List<PostDTO.Get> getAll(@PathVariable String postCategory) {
        return postService.getAll(postCategory);
    }

    @GetMapping("/post/{postId}")
    public PostDTO.Get getOne(@PathVariable Long postId) {
        return postService.getOne(postId);
    }

    @PutMapping("/post/{postId}")
    public Long update(@RequestBody PostDTO.Update dto) {
        return postService.update(dto);
    }

    @PatchMapping("/post/{postId}/check")
    public Long updateCheck(@PathVariable Long postId) {
        return postService.update(postId);
    }

    @DeleteMapping("/post/{postId}")
    public void delete(@PathVariable Long postId) {
        postService.delete(postId);
    }

    // 파일 생성
    @PostMapping("/post/{postId}/file")
    public Long addFile(@PathVariable Long postId, @RequestBody FileDTO.Create dto) {
        return postService.addFile(postId, dto);
    }

    // 파일 삭제
    @DeleteMapping("/post/{postId}/file/{fileId}")
    public void deleteFile(@PathVariable Long postId, @PathVariable Long fileId) {
        postService.deleteFile(postId, fileId);
    }

}