package kr.pe.greenthumb.dao.like;

import kr.pe.greenthumb.domain.like.LikeComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeCommentRepository extends JpaRepository<LikeComment, Long> {
//    LikeComment findByCommentIdAndUserId(Comment comment, User user);

//    List<LikeComment> findAllByCommentIdAndUserId(Comment comment, User user);
}
