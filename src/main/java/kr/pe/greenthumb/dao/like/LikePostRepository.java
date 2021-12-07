package kr.pe.greenthumb.dao.like;

import kr.pe.greenthumb.domain.like.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikePostRepository extends JpaRepository<LikePost, Long> {

//    LikePost findByPostIdAndUserId(Post post, User user);

//    List<LikePost> findAllByPostIdAndUserId(Post post, User user);

}

