package com.ssh.greenthumb.api.service;

import com.ssh.greenthumb.api.domain.post.Comment;
import com.ssh.greenthumb.api.dto.post.CommentDTO;
import com.ssh.greenthumb.api.common.exception.NotFoundException;
import com.ssh.greenthumb.api.dao.post.CommentRepository;
import com.ssh.greenthumb.api.dao.post.PostRepository;
import com.ssh.greenthumb.api.dao.user.UserRepository;
import com.ssh.greenthumb.api.domain.post.Post;
import com.ssh.greenthumb.api.domain.user.User;
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

    // 댓글 생성
    @Transactional
    public Long add(CommentDTO.Create dto) {
        Post post = postDao.findById(dto.getPostId()).
                orElseThrow(NotFoundException::new);

        User user = userDao.findById(dto.getUserId()).
                orElseThrow(NotFoundException::new);

        return commentDao.save(dto.toEntity(post, user, dto.getContent())).getId();
    }

    // 게시글별 댓글 조회
    @Transactional
    public List<CommentDTO.Get> getAllByPost(Long postId) {
        Post post = postDao.findById(postId).
                orElseThrow(NotFoundException::new);

        return commentDao.findAllByPostAndIsDeleted(post, "n").stream().map(CommentDTO.Get::new).collect(Collectors.toList());
    }

    // 유저별 댓글 조회
    @Transactional
    public List<CommentDTO.Get> getAllByUser(Long postId, Long userId) {
        Post post = postDao.findById(postId).
                orElseThrow(NotFoundException::new);

        User user = userDao.findById(userId).
                orElseThrow(NotFoundException::new);

        return commentDao.findAllByPostAndUserAndIsDeleted(post, user, "n").stream().map(CommentDTO.Get::new).collect(Collectors.toList());
    }

    // 댓글 수정
    @Transactional
    public Long update(Long commentId, CommentDTO.Update dto) {
        Comment comment = commentDao.findById(commentId).
                orElseThrow(NotFoundException::new);

        comment.update(dto.getContent());

        commentDao.save(comment);

        return commentId;
    }

    // 댓글 삭제
    @Transactional
    public String delete(Long commentId) {
        Comment comment = commentDao.findById(commentId).
                orElseThrow(NotFoundException::new);

        comment.delete();

        commentDao.save(comment);

        return comment.getIsDeleted();
    }

}