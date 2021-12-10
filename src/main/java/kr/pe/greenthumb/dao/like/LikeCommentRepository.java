package kr.pe.greenthumb.dao.like;

import kr.pe.greenthumb.domain.like.LikeComment;
import kr.pe.greenthumb.domain.post.Comment;
import kr.pe.greenthumb.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeCommentRepository extends JpaRepository<LikeComment, Long> {

    LikeComment findByCommentAndUser(Comment comment, User user);

}
