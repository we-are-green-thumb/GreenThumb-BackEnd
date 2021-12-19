package com.ssh.greenthumb.api.service;

import com.ssh.greenthumb.api.common.exception.NotFoundException;
import com.ssh.greenthumb.api.dao.like.LikeCommentRepository;
import com.ssh.greenthumb.api.dao.post.CommentRepository;
import com.ssh.greenthumb.api.dao.post.PostRepository;
import com.ssh.greenthumb.api.dao.user.UserRepository;
import com.ssh.greenthumb.api.domain.like.LikeComment;
import com.ssh.greenthumb.api.domain.post.Comment;
import com.ssh.greenthumb.api.domain.post.Post;
import com.ssh.greenthumb.api.domain.user.User;
import com.ssh.greenthumb.api.dto.like.LikeCommentDTO;
import com.ssh.greenthumb.api.dto.post.CommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final UserRepository userDao;
    private final PostRepository postDao;
    private final CommentRepository commentDao;
    private final LikeCommentRepository likeCommentDao;

    @Transactional
    public Long add(Long id, CommentDTO.Create dto) {
        Post post = postDao.findById(id).
                orElseThrow(NotFoundException::new);

        User user = userDao.findById(dto.getUserId()).
                orElseThrow(NotFoundException::new);

        return commentDao.save(dto.toEntity(post, user, dto.getContent())).getId();
    }

    @Transactional
    public List<CommentDTO.Get> getAllByPost(Long id) {
        Post post = postDao.findById(id).
                orElseThrow(NotFoundException::new);

        return commentDao.findAllByPostAndIsDeleted(post, "n").stream().map(CommentDTO.Get::new).collect(Collectors.toList());
    }

    @Transactional
    public List<CommentDTO.Get> getAllByUser(Long postId, Long userId) {
        Post post = postDao.findById(postId).
                orElseThrow(NotFoundException::new);

        User user = userDao.findById(userId).
                orElseThrow(NotFoundException::new);

        return commentDao.findAllByPostAndUserAndIsDeleted(post, user, "n").stream().map(CommentDTO.Get::new).collect(Collectors.toList());
    }

    @Transactional
    public Long update(Long commentId, CommentDTO.Update dto) {
        Comment comment = commentDao.findById(commentId).
                orElseThrow(NotFoundException::new);

        comment.update(dto.getContent());

        commentDao.save(comment);

        return commentId;
    }

    @Transactional
    public String delete(Long commentId) {
        Comment comment = commentDao.findById(commentId).
                orElseThrow(NotFoundException::new);

        comment.delete();

        commentDao.save(comment);

        return comment.getIsDeleted();
    }

    public Long likeComment(Long commentId, Long userId) {
        Comment comment = commentDao.findById(commentId).
                orElseThrow(NotFoundException::new);

        User user = userDao.findById(userId).
                orElseThrow(NotFoundException::new);

        LikeCommentDTO.Create dto = new LikeCommentDTO.Create(commentId, userId);

        return likeCommentDao.save(dto.toEntity(comment, user)).getId();
    }

    public void unLikeComment(Long commentId, Long userId) {
        Comment comment = commentDao.findById(commentId).
                orElseThrow(NotFoundException::new);

        User user = userDao.findById(userId).
                orElseThrow(NotFoundException::new);

        LikeComment likeComment = likeCommentDao.findByCommentAndUser(comment, user);

        likeCommentDao.delete(likeComment);
    }

}