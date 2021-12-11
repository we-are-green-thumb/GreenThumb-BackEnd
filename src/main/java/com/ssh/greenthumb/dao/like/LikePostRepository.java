package com.ssh.greenthumb.dao.like;

import com.ssh.greenthumb.domain.like.LikePost;
import com.ssh.greenthumb.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikePostRepository extends JpaRepository<LikePost, Long> {

    LikePost findByPost(Post post);

}

