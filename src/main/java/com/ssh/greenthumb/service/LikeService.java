package com.ssh.greenthumb.service;

import com.ssh.greenthumb.common.exception.NotFoundException;
import com.ssh.greenthumb.dao.like.LikeCommentRepository;
import com.ssh.greenthumb.dao.like.LikePostRepository;
import com.ssh.greenthumb.dao.post.CommentRepository;
import com.ssh.greenthumb.dao.post.PostRepository;
import com.ssh.greenthumb.dao.user.UserRepository;
import com.ssh.greenthumb.domain.like.LikeComment;
import com.ssh.greenthumb.domain.like.LikePost;
import com.ssh.greenthumb.domain.post.Comment;
import com.ssh.greenthumb.domain.user.User;
import com.ssh.greenthumb.dto.like.LikeCommentDTO;
import com.ssh.greenthumb.dto.like.LikePostDTO;
import com.ssh.greenthumb.domain.post.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final LikePostRepository likePostDao;
    private final LikeCommentRepository likeCommentDao;
    private final PostRepository postDao;
    private final CommentRepository commentDao;
    private final UserRepository userDao;

    // 게시글 좋아요 등록
    public Long likePost(Long postId, Long userId) {
        Post post = postDao.findById(postId).
                orElseThrow(NotFoundException::new);

        User user = userDao.findById(userId).
                orElseThrow(NotFoundException::new);

        LikePostDTO.Create likePost = new LikePostDTO.Create(postId, userId);

        return likePostDao.save(likePost.toEntity(post, user)).getLikePostId();
    }

    // 게시글 좋아요 취소
    public void unLikePost(Long postId, Long userId) {
        Post post = postDao.findById(postId).
                orElseThrow(NotFoundException::new);

        User user = userDao.findById(userId).
                orElseThrow(NotFoundException::new);

        LikePost likePost = likePostDao.findByPost(post);

        likePostDao.delete(likePost);
    }

    // 댓글 좋아요 등록
    public Long likeComment(Long commentId, Long userId) {
        Comment comment = commentDao.findById(commentId).
                orElseThrow(NotFoundException::new);

        User user = userDao.findById(userId).
                orElseThrow(NotFoundException::new);

        LikeCommentDTO.Create dto = new LikeCommentDTO.Create(commentId, userId);

        return likeCommentDao.save(dto.toEntity(comment, user)).getLikeCommentId();
    }

    // 댓글 좋아요 취소
    public void unLikeComment(Long commentId, Long userId) {
        Comment comment = commentDao.findById(commentId).
                orElseThrow(NotFoundException::new);

        User user = userDao.findById(userId).
                orElseThrow(NotFoundException::new);

        LikeComment likeComment = likeCommentDao.findByCommentAndUser(comment, user);

        likeCommentDao.delete(likeComment);
    }

}