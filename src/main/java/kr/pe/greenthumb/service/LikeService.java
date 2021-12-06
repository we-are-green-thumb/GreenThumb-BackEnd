package kr.pe.greenthumb.service;

import kr.pe.greenthumb.common.exception.NotFoundException;
import kr.pe.greenthumb.dao.like.LikeCommentRepository;
import kr.pe.greenthumb.dao.like.LikePostRepository;
import kr.pe.greenthumb.dao.post.CommentRepository;
import kr.pe.greenthumb.dao.post.PostRepository;
import kr.pe.greenthumb.dao.user.UserRepository;
import kr.pe.greenthumb.domain.like.LikeComment;
import kr.pe.greenthumb.domain.like.LikePost;
import kr.pe.greenthumb.domain.post.Comment;
import kr.pe.greenthumb.domain.post.Post;
import kr.pe.greenthumb.domain.user.User;
import kr.pe.greenthumb.dto.like.LikeCommentDTO;
import kr.pe.greenthumb.dto.like.LikePostDTO;
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
                orElseThrow(() -> new NotFoundException("This (number" + postId + ") post is not exist"));

        User user = userDao.findById(userId).
                orElseThrow(() -> new NotFoundException("This (number" + userId + ") user is not exist"));

        LikePostDTO.Create likePost = new LikePostDTO.Create(postId, userId);

        return likePostDao.save(likePost.toEntity(post, user)).getLikePostId();
    }

    // 게시글 좋아요 취소
    public void unLikePost(Long likePostId) {
        LikePost likePost = likePostDao.findById(likePostId).
                orElseThrow(() -> new NotFoundException("This (number" + likePostId + ") likePost is not exist"));

        likePostDao.delete(likePost);
    }

    // 댓글 좋아요 등록
    public Long likeComment(Long commentId, Long userId) {
        Comment comment = commentDao.findById(commentId).
                orElseThrow(() -> new NotFoundException("This (number" + commentId + ") comment is not exist"));

        User user = userDao.findById(userId).
                orElseThrow(() -> new NotFoundException("This (number" + userId + ") user is not exist"));

        LikeCommentDTO.Create dto = new LikeCommentDTO.Create(commentId, userId);

        return likeCommentDao.save(dto.toEntity(comment, user)).getLikeCommentId();
    }

    // 댓글 좋아요 취소
    public void unLikeComment(Long likeCommentId) {
        LikeComment likeComment = likeCommentDao.findById(likeCommentId).
                orElseThrow(() -> new NotFoundException("This (number" + likeCommentId + ") likeCommentId is not exist"));

        likeCommentDao.delete(likeComment);
    }

}
