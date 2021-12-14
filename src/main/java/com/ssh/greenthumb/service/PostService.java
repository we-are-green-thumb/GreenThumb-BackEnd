package com.ssh.greenthumb.service;

import com.ssh.greenthumb.dto.post.FileDTO;
import com.ssh.greenthumb.dto.post.PostDTO;
import com.ssh.greenthumb.common.exception.NotFoundException;
import com.ssh.greenthumb.dao.post.FileRepository;
import com.ssh.greenthumb.dao.post.PostRepository;
import com.ssh.greenthumb.dao.user.UserRepository;
import com.ssh.greenthumb.domain.post.File;
import com.ssh.greenthumb.domain.post.Post;
import com.ssh.greenthumb.domain.user.User;
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

        return postDao.save(dto.toEntity(user, dto.getTitle(), dto.getContent(), dto.getCategory())).getId();
    }

    @Transactional
    public List<PostDTO.Get> getAll(String postCategory) {
        return postDao.findAllPostByCategoryAndIsDeleted(postCategory, "n").stream().map(PostDTO.Get::new).collect(Collectors.toList());
    }

    @Transactional
    public PostDTO.Get getOne(Long postId) {
        return postDao.findById(postId).map(PostDTO.Get::new).get();
    }

    @Transactional
    public Long update(Long postId, PostDTO.Update dto) {
        Post post = postDao.findById(postId).
                orElseThrow(NotFoundException::new);

        post.update(dto.getTitle(), dto.getCategory(), dto.getContent());

        return post.getId();
    }

    @Transactional
    public Long updateCheck(Long postId) {
        Post post = postDao.findById(postId).
                orElseThrow(NotFoundException::new);

        post.updateCheck(post.getIsComplete());

        return post.getId();
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

        return fileDao.save(dto.toEntity(post, dto.getFileUrl())).getId();
    }

    // 파일 삭제
    @Transactional
    public void deleteFile(Long fileId) {
        File file = fileDao.findById(fileId).
                orElseThrow(NotFoundException::new);

        fileDao.delete(file);
    }

}