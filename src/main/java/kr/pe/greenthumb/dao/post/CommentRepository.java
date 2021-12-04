package kr.pe.greenthumb.dao.post;

import kr.pe.greenthumb.domain.post.Comment;
import kr.pe.greenthumb.domain.post.Post;
import kr.pe.greenthumb.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPostAndUser(Post post, User user, String commentDelete);

    List<Comment> findAllByPost(Post post, String commentDelete);

}