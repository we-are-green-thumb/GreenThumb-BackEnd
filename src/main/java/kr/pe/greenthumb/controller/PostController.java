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
@RequestMapping("/post")
@RestController
public class PostController {

    private final PostService postService;

<<<<<<< HEAD
    // Post ===========================================================================================================
    // 게시글 생성
    @PostMapping("/post/user/{userId}")
=======
    //Q 카테고리명 어떻게 할지!
    @PostMapping
>>>>>>> c18116ba57b6abd0a279f4167146da1d48e1b720
    public Long add(@RequestBody PostDTO.Create dto) {
        return postService.add(dto);
    }

<<<<<<< HEAD
    // 카테고리별 전체 게시글 검색
    @GetMapping("/post/{postCategory}")
=======
    @GetMapping("/posts/{postCategory}")
>>>>>>> c18116ba57b6abd0a279f4167146da1d48e1b720
    public List<PostDTO.Get> getAll(@PathVariable String postCategory) {
        return postService.getAll(postCategory);
    }

<<<<<<< HEAD
    // 게시글 하나 검색
    @GetMapping("/post/{postId}")
=======
    @GetMapping("/{postId}")
>>>>>>> c18116ba57b6abd0a279f4167146da1d48e1b720
    public PostDTO.Get getOne(@PathVariable Long postId) {
        return postService.getOne(postId);
    }

<<<<<<< HEAD
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
=======
    @PutMapping("/{postId}")
    public Long update(@PathVariable Long postId, @RequestBody PostDTO.Update dto) {
        return postService.update(postId, dto);
    }

    @PatchMapping("/{postId}/check")
    public Long updateCheck(@PathVariable Long postId) {
        return postService.updateCheck(postId);
    }

    @DeleteMapping("/{postId}")
>>>>>>> c18116ba57b6abd0a279f4167146da1d48e1b720
    public void delete(@PathVariable Long postId) {
        postService.delete(postId);
    }

<<<<<<< HEAD
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
=======
    @PostMapping("/{postId}/file")
>>>>>>> c18116ba57b6abd0a279f4167146da1d48e1b720
    public Long addFile(@PathVariable Long postId, @RequestBody FileDTO.Create dto) {
        return postService.addFile(postId, dto);
    }

    @DeleteMapping("/file/{fileId}")
    public void deleteFile(@PathVariable Long fileId) {
        postService.deleteFile(fileId);
    }

}