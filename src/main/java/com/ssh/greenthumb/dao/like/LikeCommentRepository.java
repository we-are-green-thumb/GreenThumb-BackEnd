package com.ssh.greenthumb.dao.like;

import com.ssh.greenthumb.domain.like.LikeComment;
import com.ssh.greenthumb.domain.post.Comment;
import com.ssh.greenthumb.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeCommentRepository extends JpaRepository<LikeComment, Long> {

    LikeComment findByCommentAndUser(Comment comment, User user);

}
