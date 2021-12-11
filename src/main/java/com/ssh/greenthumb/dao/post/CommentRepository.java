package com.ssh.greenthumb.dao.post;

import com.ssh.greenthumb.domain.post.Comment;
import com.ssh.greenthumb.domain.post.Post;
import com.ssh.greenthumb.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPostAndUserAndIsDeleted(Post post, User user, String isDeleted);

    List<Comment> findAllByPostAndIsDeleted(Post post, String isDeleted);

}