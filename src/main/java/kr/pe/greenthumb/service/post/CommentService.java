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
    public Long add(Long postIdx, Long userIdx, CommentDTO.Create dto) {

        Post post = postDao.findById(postIdx).
                orElseThrow(() -> new NotFoundException("This (number" + postIdx + ") post is not exist"));

        User user = userDao.findById(userIdx).
                orElseThrow(() -> new NotFoundException("This (number" + userIdx + ") user is not exist"));

        return commentDao.save(dto.toEntity(post, user)).getCommentIdx();

    }

    // 게시글별 댓글 조회
    @Transactional
    public List<CommentDTO.Get> getAllByPost(Long postIdx) {

        Post post = postDao.findById(postIdx).
                orElseThrow(() -> new NotFoundException("This (number" + postIdx + ") post is not exist"));

        return commentDao.findAllByPostAndIsDeleted(post, "n").stream().map(CommentDTO.Get::new).collect(Collectors.toList());

    }

    // 유저별 댓글 조회
    @Transactional
    public List<CommentDTO.Get> getAllByUser(Long postIdx, Long userIdx) {

        Post post = postDao.findById(postIdx).
                orElseThrow(() -> new NotFoundException("This (number" + postIdx + ") post is not exist"));

        User user = userDao.findById(userIdx).
                orElseThrow(() -> new NotFoundException("This (number" + userIdx + ") user is not exist"));

        return commentDao.findAllByPostAndUserAndIsDeleted(post, user, "n").stream().map(CommentDTO.Get::new).collect(Collectors.toList());
    }

    // 댓글 수정
    @Transactional
    public Long update(Long postIdx, Long userIdx, Long commentIdx, CommentDTO.Update dto) {

        Post post = postDao.findById(postIdx).
                orElseThrow(() -> new NotFoundException("This (number" + postIdx + ") post is not exist"));

        User user = userDao.findById(userIdx).
                orElseThrow(() -> new NotFoundException("This (number" + userIdx + ") user is not exist"));

        Comment comment = commentDao.findById(commentIdx).
                orElseThrow(() -> new NotFoundException("This (number" + commentIdx + ") comment is not exist"));

        comment.update(commentIdx, post, user, dto.getCommentContent());

        return commentIdx;

    }

    // 댓글 삭제
    @Transactional
    public void delete(Long commentIdx) {

        Comment comment = commentDao.findById(commentIdx).
                orElseThrow(() -> new NotFoundException("This (number" + commentIdx + ") comment is not exist"));

        comment.delete();

        commentDao.save(comment);

    }

}