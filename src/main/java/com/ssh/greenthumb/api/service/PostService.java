package com.ssh.greenthumb.api.service;

import com.ssh.greenthumb.api.common.exception.NotFoundException;
import com.ssh.greenthumb.api.dao.like.LikePostRepository;
import com.ssh.greenthumb.api.dao.post.PostRepository;
import com.ssh.greenthumb.api.dao.user.UserRepository;
import com.ssh.greenthumb.api.domain.like.LikePost;
import com.ssh.greenthumb.api.domain.post.Post;
import com.ssh.greenthumb.api.domain.user.User;
import com.ssh.greenthumb.api.dto.like.LikePostDTO;
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
    private final LikePostRepository likePostDao;

    @Transactional
    public Long add(Long id, PostDTO.Create dto) {
        User user = userDao.findById(id).
                orElseThrow(NotFoundException::new);

        Post post = postDao.save(dto.toEntity(user, dto.getTitle(), dto.getCategory(), dto.getContent(),dto.getFileUrl()));

        return post.getId();
    }

    @Transactional
    public List<PostDTO.Get> getAll() {
        List<Post> posts = postDao.findAllByIsDeleted("n");

        List<PostDTO.Get> dtos = posts.stream().map(PostDTO.Get::new).collect(Collectors.toList());

        for (int i = 0; i<posts.size(); i++) {
            dtos.get(i).setLike(posts.get(i).getLikePostList().size());
        }

        return dtos;
    }

    @Transactional
    public List<PostDTO.Get> getAllByCategory(String category) {
        return postDao.findAllPostByCategoryAndIsDeleted(category, "n").stream().map(PostDTO.Get::new).collect(Collectors.toList());
    }

    public List<PostDTO.Get> getAllByUser(Long id) {
        User user = userDao.findById(id).get();

        return postDao.findAllPostByUserAndIsDeleted(user, "n").stream().map(PostDTO.Get::new).collect(Collectors.toList());
    }

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

    public String likePost(Long postId, Long userId) {
        Post post = postDao.findById(postId).
                orElseThrow(NotFoundException::new);

        User user = userDao.findById(userId).
                orElseThrow(NotFoundException::new);

        LikePost likePost = likePostDao.findByPostAndUser(post, user);

        LikePostDTO dto = new LikePostDTO();

        if (likePost == null) {
            likePostDao.save(dto.toEntity(post, user));

            return "좋아요 완료";
        } else {
            likePostDao.delete(likePost);

            return "좋아요 취소 완료";
        }
    }

}