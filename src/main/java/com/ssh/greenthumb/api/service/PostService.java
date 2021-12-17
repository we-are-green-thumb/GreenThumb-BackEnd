package com.ssh.greenthumb.api.service;

import com.ssh.greenthumb.api.common.exception.NotFoundException;
import com.ssh.greenthumb.api.dao.post.PostRepository;
import com.ssh.greenthumb.api.dao.user.UserRepository;
import com.ssh.greenthumb.api.domain.post.Post;
import com.ssh.greenthumb.api.domain.user.User;
import com.ssh.greenthumb.api.dto.post.PostDTO;
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

    @Transactional
    public Long add(Long id, PostDTO.Create dto) {
        User user = userDao.findById(id).
                orElseThrow(NotFoundException::new);

        Post post = postDao.save(dto.toEntity(user, dto.getTitle(), dto.getCategory(), dto.getContent()));

        return post.getId();
    }

    @Transactional
    public List<PostDTO.Get> getAll() {
        return postDao.findAllByIsDeleted("n").stream().map(PostDTO.Get::new).collect(Collectors.toList());
    }

    @Transactional
    public List<PostDTO.Get> getAllByCategory(String category) {
        return postDao.findAllPostByCategoryAndIsDeleted(category, "n").stream().map(PostDTO.Get::new).collect(Collectors.toList());
    }

    @Transactional
    public PostDTO.Get getOne(Long id) {
        return postDao.findById(id).map(PostDTO.Get::new).get();
    }

    @Transactional
    public Long update(Long id, PostDTO.Update dto) {
        Post post = postDao.findById(id).
                orElseThrow(NotFoundException::new);

        post.update(dto.getTitle(), dto.getCategory(), dto.getContent(), dto.getFileUrl());

        return post.getId();
    }

    @Transactional
    public Long updateCheck(Long id) {
        Post post = postDao.findById(id).
                orElseThrow(NotFoundException::new);

        post.updateCheck(post.getIsComplete());

        return post.getId();
    }

    @Transactional
    public void delete(Long id) {
        Post post = postDao.findById(id)
                .orElseThrow(NotFoundException::new);

        post.delete();
    }

}