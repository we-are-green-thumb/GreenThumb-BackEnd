package kr.pe.greenthumb.service.post;

import kr.pe.greenthumb.common.exception.NotFoundException;
import kr.pe.greenthumb.dao.post.FileRepository;
import kr.pe.greenthumb.dao.post.PostRepository;
import kr.pe.greenthumb.dto.post.FileDTO;
import kr.pe.greenthumb.domain.post.Post;
import kr.pe.greenthumb.domain.post.File;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FileService {

    private final PostRepository postDao;
    private final FileRepository fileDao;

    // 파일 생성
    @Transactional
    public Long add(Long postId, FileDTO.Create dto) {
        Post post = postDao.findById(postId).
                orElseThrow(NotFoundException::new);

        return fileDao.save(dto.toEntity(post, dto.getFileUrl())).getFileId();
    }

    // 파일 삭제
    @Transactional
    public void delete(Long postId, Long fileId) {
        Post post = postDao.findById(postId).
                orElseThrow(NotFoundException::new);

        File file = fileDao.findById(fileId).
                orElseThrow(NotFoundException::new);

        fileDao.deleteByPost(post);
    }

}