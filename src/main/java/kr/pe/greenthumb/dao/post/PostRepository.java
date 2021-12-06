package kr.pe.greenthumb.dao.post;

import kr.pe.greenthumb.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findPostByPostCategory(String postCategory);

}
