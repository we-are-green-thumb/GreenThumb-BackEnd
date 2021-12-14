package com.ssh.greenthumb.dao.post;

import com.ssh.greenthumb.domain.post.Post;
import com.ssh.greenthumb.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>, PagingAndSortingRepository<Post, Long> {

    List<Post> findAllPostByCategoryAndIsDeleted(String postCategory, String isDeleted);

    List<Post> findByUser(User user);

}
