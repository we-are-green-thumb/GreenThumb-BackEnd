package kr.pe.greenthumb.service;

import kr.pe.greenthumb.common.exception.NotFoundException;
import kr.pe.greenthumb.dao.post.FileRepository;
import kr.pe.greenthumb.dao.post.PostRepository;
import kr.pe.greenthumb.dao.user.UserRepository;
import kr.pe.greenthumb.domain.post.File;
import kr.pe.greenthumb.domain.post.Post;
import kr.pe.greenthumb.domain.user.User;
import kr.pe.greenthumb.dto.post.FileDTO;
import kr.pe.greenthumb.dto.post.PostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postDao;
    private final UserRepository userDao;
    private final FileRepository fileDao;

    @Transactional
    public Long add(PostDTO.Create dto) {
        User user = userDao.findById(dto.getUserId()).
                orElseThrow(NotFoundException::new);

        return postDao.save(dto.toEntity(user, dto.getTitle(), dto.getPostContent(), dto.getPostCategory())).getPostId();
    }

    @Transactional
    public List<PostDTO.Get> getAll(String postCategory) {
        return postDao.findAllPostByPostCategoryAndIsDeleted(postCategory, "n").stream().map(PostDTO.Get::new).collect(Collectors.toList());
    }

    @Transactional
    public PostDTO.Get getOne(Long postId) {
        return postDao.findById(postId).map(PostDTO.Get::new).get();
    }

    @Transactional
    public Long update(PostDTO.Update dto) {
        Post post = postDao.findById(dto.getPostId()).
                orElseThrow(NotFoundException::new);

        post.update(dto.getTitle(), dto.getPostCategory(), dto.getPostContent());

        return post.getPostId();
    }

    @Transactional
    public Long updateCheck(Long postId) {
        Post post = postDao.findById(postId).
                orElseThrow(NotFoundException::new);

        post.updateCheck(postId);

        return post.getPostId();
    }

    @Transactional
    public void delete(Long postId) {
        Post post = postDao.findById(postId)
                .orElseThrow(NotFoundException::new);

        post.delete();
    }

    // 파일 생성
    @Transactional
    public Long addFile(Long postId, FileDTO.Create dto) {
        Post post = postDao.findById(postId).
                orElseThrow(NotFoundException::new);

        return fileDao.save(dto.toEntity(post, dto.getFileUrl())).getFileId();
    }

    // 파일 삭제
    @Transactional
    public void deleteFile(Long postId, Long fileId) {
        Post post = postDao.findById(postId).
                orElseThrow(NotFoundException::new);

        File file = fileDao.findById(fileId).
                orElseThrow(NotFoundException::new);

        fileDao.deleteByPost(post);
    }

}