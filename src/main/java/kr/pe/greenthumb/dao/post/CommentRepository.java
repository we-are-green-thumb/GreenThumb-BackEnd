package kr.pe.greenthumb.dao.post;

import kr.pe.greenthumb.domain.post.Comment;
import kr.pe.greenthumb.domain.post.Post;
import kr.pe.greenthumb.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findByPostIdx(Post post);

    List<Comment> findAllByPostIdx(Post post);

    List<Comment> findByPostIdxAndUserIdx(Post post, User user);
}
