package kr.pe.greenthumb.dao.like;

import kr.pe.greenthumb.domain.like.LikeComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeCommentRepository extends JpaRepository<LikeComment, Long> {
//    LikeComment findByCommentIdxAndUserIdx(Comment comment, User user);

//    List<LikeComment> findAllByCommentIdxAndUserIdx(Comment comment, User user);
}
