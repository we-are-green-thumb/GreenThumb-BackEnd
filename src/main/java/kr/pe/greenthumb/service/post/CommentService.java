package kr.pe.greenthumb.service.post;

import kr.pe.greenthumb.common.exception.NotFoundException;
import kr.pe.greenthumb.dao.post.CommentRepository;
import kr.pe.greenthumb.dao.post.PostRepository;
import kr.pe.greenthumb.dao.user.UserRepository;
import kr.pe.greenthumb.domain.post.Comment;
import kr.pe.greenthumb.domain.post.Post;
import kr.pe.greenthumb.domain.user.User;
import kr.pe.greenthumb.dto.post.CommentDTO;
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

        return commentDao.save(dto.toEntity(post, user, dto.getCommentContent())).getCommentId();
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

        comment.update(dto.getCommentContent());

        commentDao.save(comment);

        return commentId;
    }

    // 댓글 삭제
    @Transactional
    public void delete(Long commentId) {
        Comment comment = commentDao.findById(commentId).
                orElseThrow(NotFoundException::new);

        comment.delete();

        commentDao.save(comment);
    }

}