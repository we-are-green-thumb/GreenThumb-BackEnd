package kr.pe.greenthumb.service.post;

import kr.pe.greenthumb.common.exception.NotFoundException;
import kr.pe.greenthumb.dao.post.PostRepository;
import kr.pe.greenthumb.dao.post.FileRepository;
import kr.pe.greenthumb.domain.post.File;
import kr.pe.greenthumb.domain.post.Post;
import kr.pe.greenthumb.dto.post.FileDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileUrlResource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FileService {

    private final PostRepository postDao;
    private final FileRepository fileDao;

    //경로 생성
    @Transactional
    public Long add(Long postId, Long fileId, FileDTO.Create dto){
        Post post = postDao.findById(postId).
                orElseThrow(NotFoundException::new);

        return fileDao.save(dto.toEntity(post, dto.getFileUrl())).getFileId();
    }

    // 경로 수정
    @Transactional
    public Long update(Long postId, Long fileId, FileDTO.Update dto) {
        Post post = postDao.findById(postId).
                orElseThrow(NotFoundException::new)

        File file = fileDao.findById(fileId).
                orElseThrow(NotFoundException::new);

        file.update(postId, fileId, dto.getFileUrl());

        return
    }

    @Transactional
    public void delete(Long fileId) {
        File file = fileDao.findById(fileId).
                orElseThrow(NotFoundException::new);

        file.delete();

        fileDao.save(file);
    }

}
