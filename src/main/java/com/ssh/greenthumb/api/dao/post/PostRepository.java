package com.ssh.greenthumb.api.dao.post;

import com.ssh.greenthumb.api.domain.post.Post;
import com.ssh.greenthumb.api.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>, PagingAndSortingRepository<Post, Long> {

    List<Post> findAllByIsDeleted(String isDeleted);

    List<Post> findAllPostByCategoryAndIsDeleted(String postCategory, String isDeleted);

    List<Post> findByUser(User user);

    List<Post> findAllPostByUserAndIsDeleted(User user, String isDeleted);
}
