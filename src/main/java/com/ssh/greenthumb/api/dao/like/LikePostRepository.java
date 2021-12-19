package com.ssh.greenthumb.api.dao.like;

import com.ssh.greenthumb.api.domain.like.LikePost;
import com.ssh.greenthumb.api.domain.post.Post;
import com.ssh.greenthumb.api.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikePostRepository extends JpaRepository<LikePost, Long> {

    LikePost findByPost(Post post);

    LikePost findByPostAndUser(Post post, User user);

}

