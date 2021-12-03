package kr.pe.greenthumb.dao.like;

import kr.pe.greenthumb.domain.like.LikePost;
import kr.pe.greenthumb.domain.post.Post;
import kr.pe.greenthumb.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikePostRepository extends JpaRepository<LikePost, Long> {
    LikePost findByPostIdxAndUserIdx(Post post, User user);

    List<LikePost> findAllByPostIdxAndUserIdx(Post post, User user);
}
