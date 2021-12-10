package kr.pe.greenthumb.controller;

import kr.pe.greenthumb.dto.like.LikePostDTO;
import kr.pe.greenthumb.dto.post.FileDTO;
import kr.pe.greenthumb.dto.post.PostDTO;
import kr.pe.greenthumb.service.LikeService;
import kr.pe.greenthumb.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    // Post ===========================================================================================================
    // 게시글 생성
    @PostMapping("/post/user/{userId}")
    public Long add(@RequestBody PostDTO.Create dto) {
        return postService.add(dto);
    }

    // 카테고리별 전체 게시글 검색
    @GetMapping("/post/{postCategory}")
    public List<PostDTO.Get> getAll(@PathVariable String postCategory) {
        return postService.getAll(postCategory);
    }

    // 게시글 하나 검색
    @GetMapping("/post/{postId}")
    public PostDTO.Get getOne(@PathVariable Long postId) {
        return postService.getOne(postId);
    }

    // 게시글 수정
    @PutMapping("/post/{postId}")
    public Long update(@RequestBody PostDTO.Update dto) {
        return postService.update(dto);
    }

    // 질문&나눔&거래 게시판 완료 여부
    @PutMapping("/post/{postId}/check")
    public Long updateCheck(@RequestBody PostDTO.UpdateCheck dto) {
        return postService.updateCheck(dto);
    }

    // 게시글 삭제
    @DeleteMapping("/post/{postId}")
    public void delete(@PathVariable Long postId) {
        postService.delete(postId);
    }

    // Post Like ======================================================================================================
    // 게시글 추천
    @PostMapping("/post/{id}/like")
    public void likePost(@PathVariable Long postId, @RequestBody LikePostDTO.Create dto) {
        LikeService.likePost(postId, dto.getUserId());
    }

    // 게시글 추천 취소
    @DeleteMapping("/post/{id}/like")
    public void unLikePost(@PathVariable Long likePostId) {
        LikeService.unLikePost(likePostId);
    }

    // 게시글별 추천 카운트
    @GetMapping("/post/{id}/like")
    public Long countLikePost(@PathVariable Long postId) {
        return LikeService.countLike(postId);
    }



    // File ===========================================================================================================
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