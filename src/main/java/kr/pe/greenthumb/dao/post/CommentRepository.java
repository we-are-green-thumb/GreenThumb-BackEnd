package kr.pe.greenthumb.dao.post;

import kr.pe.greenthumb.domain.post.Comment;
import kr.pe.greenthumb.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findCommentByPost(Post post);

    List<Comment> findAllByPost(Post post);

//    List<Comment> findCommentByPostIdxAndUserIdx(Long postIdx, Long userIdx);
}