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
    public Long likePost(Long postIdx, Long userIdx) {
        Post post = postDao.findById(postIdx).
                orElseThrow(() -> new NotFoundException("This (number" + postIdx + ") post is not exist"));

        User user = userDao.findById(userIdx).
                orElseThrow(() -> new NotFoundException("This (number" + userIdx + ") user is not exist"));

        LikePostDTO.Create likePost = new LikePostDTO.Create(postIdx, userIdx);

        return likePostDao.save(likePost.toEntity(post, user)).getLikePostIdx();
    }

    // 게시글 좋아요 취소
    public void unLikePost(Long likePostIdx) {
        LikePost likePost = likePostDao.findById(likePostIdx).
                orElseThrow(() -> new NotFoundException("This (number" + likePostIdx + ") likePost is not exist"));

        likePostDao.delete(likePost);
    }

    // 댓글 좋아요 등록
    public Long likeComment(Long commentIdx, Long userIdx) {
        Comment comment = commentDao.findById(commentIdx).
                orElseThrow(() -> new NotFoundException("This (number" + commentIdx + ") comment is not exist"));

        User user = userDao.findById(userIdx).
                orElseThrow(() -> new NotFoundException("This (number" + userIdx + ") user is not exist"));

        LikeCommentDTO.Create dto = new LikeCommentDTO.Create(commentIdx, userIdx);

        return likeCommentDao.save(dto.toEntity(comment, user)).getLikeCommentIdx();
    }

    // 댓글 좋아요 취소
    public void unLikeComment(Long likeCommentIdx) {
        LikeComment likeComment = likeCommentDao.findById(likeCommentIdx).
                orElseThrow(() -> new NotFoundException("This (number" + likeCommentIdx + ") likeCommentIdx is not exist"));

        likeCommentDao.delete(likeComment);
    }

}
