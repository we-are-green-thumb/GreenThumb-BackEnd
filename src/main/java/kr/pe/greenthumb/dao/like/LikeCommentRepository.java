package kr.pe.greenthumb.dao.like;

import kr.pe.greenthumb.domain.like.LikeComment;
import kr.pe.greenthumb.domain.post.Comment;
import kr.pe.greenthumb.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeCommentRepository extends JpaRepository<LikeComment, Long> {
    LikeComment findByCommentIdxAndUserIdx(Comment comment, User user);

    List<LikeComment> findAllByCommentIdxAndUserIdx(Comment comment, User user);
}
