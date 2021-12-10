package kr.pe.greenthumb.dao.like;

import kr.pe.greenthumb.domain.like.LikePost;
import kr.pe.greenthumb.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikePostRepository extends JpaRepository<LikePost, Long> {

    LikePost findByPost(Post post);

}

