package kr.pe.greenthumb.dao.post;

import kr.pe.greenthumb.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepository extends JpaRepository<Post, Long>, PagingAndSortingRepository<Post, Long> {
=======
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findPostByPostCategory(String postCategory);
>>>>>>> 112c1f666cf9f84a8ec5b57481a1ccd000cefa3a
}
