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

    // 경로 생성
    @PostMapping("/post/{postId}/file/file{fileId}")
    public Long add(@PathVariable Long postId, @PathVariable Long fileId, @RequestBody FileDTO.Create dto) {
        return fileService.add(postId, fileId, dto);
    }

    // 경로 수정
    @PutMapping("/post/{postId}/fileId/{fileId}/fileUrl/{fileUrl}")
    public Long update(@PathVariable Long postId, @PathVariable Long fileId, @PathVariable Long fileUrl, @RequestBody FileDTO.Update dto) {
        return fileService.update(postId, fileId, fileUrl, dto);
    }

    // 경로 삭제
    @DeleteMapping("/fileId/{fileId}")
    public void delete(@PathVariable Long fileId) {
        fileService.delete(fileId);
    }

}
