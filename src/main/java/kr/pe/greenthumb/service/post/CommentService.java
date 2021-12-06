package kr.pe.greenthumb.service.post;

import kr.pe.greenthumb.dao.post.CommentRepository;
import kr.pe.greenthumb.dao.post.PostRepository;
import kr.pe.greenthumb.dao.user.UserRepository;
import kr.pe.greenthumb.domain.BaseTimeEntity;
import kr.pe.greenthumb.domain.post.Post;
import kr.pe.greenthumb.domain.user.User;
import kr.pe.greenthumb.dto.post.CommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService extends BaseTimeEntity {

    @Autowired
    private UserRepository userDao;
    @Autowired
    private PostRepository postDao;
    @Autowired
    private CommentRepository commentDao;

    // 댓글 생성
    @Transactional
    public Long save(Long postId, CommentDTO.Create dto) {
        Post post = postDao.findById(postId).
                orElseThrow(() -> new NullPointerException("Post with id: " + postId + " is not valid"));

        User user = userDao.findById(dto.getUserIdx())
                .orElseThrow(() -> new NullPointerException("User with id: " + dto.getUserIdx() + " is not valid"));

        return commentDao.save(dto.toEntity(post, user)).getCommentIdx();
    }

    // 댓글 수정
    @Transactional
    public Long update(Long postId, CommentDTO.Update dto) {
        return null;
    }
}