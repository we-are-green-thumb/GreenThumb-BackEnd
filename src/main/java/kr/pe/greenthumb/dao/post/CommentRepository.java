package kr.pe.greenthumb.dao.post;

import kr.pe.greenthumb.domain.post.Comment;
import kr.pe.greenthumb.domain.post.Post;
import kr.pe.greenthumb.domain.post.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPostAndUserAndIsDeleted(Post post, User user, String isDeleted);

    List<Comment> findAllByPostAndIsDeleted(Post post, String isDeleted);

}