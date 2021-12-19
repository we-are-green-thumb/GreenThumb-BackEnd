package com.ssh.greenthumb.api.controller;

import com.ssh.greenthumb.api.dto.post.PostDTO;
import com.ssh.greenthumb.api.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Community", description = "게시판 API")
@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @Operation(summary = "게시글 등록", description = "사용자의 id와 게시글 제목, 카테고리, 내용, 첨부파일 경로를 입력하여 게시글을 등록합니다.")
    @PostMapping("/user/{id}/post")
    public Long add(@PathVariable Long id, @RequestBody PostDTO.Create dto) {
        return postService.add(id, dto);
    }

    @Operation(summary = "게시글 전체 조회", description = "등록된 게시글을 전체 조회 합니다.")
    @GetMapping("/posts")
    public List<PostDTO.Get> getAll() {
        return postService.getAll();
    }

    @Operation(summary = "카테고리별 게시글 전체 조회", description = "카테고리명으로 해당 게시글을 전체 조회 합니다.")
    @GetMapping("/posts/category/{category}")
    public List<PostDTO.Get> getAllByCategory(@PathVariable String category) {
        return postService.getAllByCategory(category);
    }

    @Operation(summary = "사용자별 게시글 전체 조회", description = "사용자 id로 해당 사용자가 작성한 게시글을 전체 조회 합니다.")
    @GetMapping("/posts/user/{id}")
    public List<PostDTO.Get> getAllByUser(@PathVariable Long id) {
        return postService.getAllByUser(id);
    }

    @Operation(summary = "게시글 상세 조회", description = "게시글 id로 해당 게시글의 상세 정보를 조회합니다.")
    @GetMapping("/post/{id}")
    public PostDTO.Get getOne(@PathVariable Long id) {
        return postService.getOne(id);
    }

    @Operation(summary = "게시글 수정", description = "게시글의 id로 제목, 카테고리, 내용, 첨부파일 경로를 수정합니다.")
    @PutMapping("/post/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostDTO.Update dto) {
        return postService.update(id, dto);
    }

    @Operation(summary = "게시글 완료 여부 수정", description = "게시글 id로 완료 여부를 수정합니다.")
    @PutMapping("/post/{id}/check")
    public Long updateCheck(@PathVariable Long id) {
        return postService.updateCheck(id);
    }

    @Operation(summary = "게시글 삭제", description = "게시글 id로 해당 게시글을 삭제 합니다.")
    @DeleteMapping("/post/{id}")
    public void delete(@PathVariable Long id) {
        postService.delete(id);
    }

    @Operation(summary = "게시글 좋아요 등록", description = "게시글 id와 사용자 id로 좋아요를 등록하거나 이미 등록된 좋아요를 취소합니다.")
    @PostMapping("/post/{postId}/user/{userId}/like")
    public String likePost(@PathVariable Long postId, @PathVariable Long userId) {
        return postService.likePost(postId, userId);
    }

}