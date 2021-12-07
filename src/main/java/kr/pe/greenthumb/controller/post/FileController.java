package kr.pe.greenthumb.controller.post;

import kr.pe.greenthumb.service.post.FileService;
import kr.pe.greenthumb.dto.post.FileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FileController {

    private  final FileService fileService;

    // 파일 생성
    @PostMapping("/post/{postId}/file")
    public Long add(@PathVariable Long postId, @RequestBody FileDTO.Create dto) {
        return fileService.add(postId, dto);
    }

    // 파일 삭제
    @DeleteMapping("/post/{postId}/file/{fileId}")
    public void delete(@PathVariable Long postId, @PathVariable Long fileId) {
        fileService.delete(postId, fileId);
    }

}
