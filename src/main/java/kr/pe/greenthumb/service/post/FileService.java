package kr.pe.greenthumb.service.post;

import kr.pe.greenthumb.common.exception.NotFoundException;
import kr.pe.greenthumb.dao.post.PostRepository;
import kr.pe.greenthumb.dao.post.FileRepository;
import kr.pe.greenthumb.domain.post.File;
import kr.pe.greenthumb.domain.post.Post;
import kr.pe.greenthumb.dto.post.FileDTO;
import lombok.RequiredArgsConstructor;
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
    public Long add(Long postId, Long fileUrl, FileDTO.Create dto){
        Post post = postDao.findById(postId).
                orElseThrow(NotFoundException::new);

        return fileDao.save(dto.toEntity(post, dto.getFileUrl())).getFileId();
    }

    public List<File> getAll(String category) {
        return fileDao.findAllByPostAndFileUrl();
    }
}
