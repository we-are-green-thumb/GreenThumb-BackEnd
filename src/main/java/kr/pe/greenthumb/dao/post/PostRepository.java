package kr.pe.greenthumb.dao.post;

import kr.pe.greenthumb.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;



public interface PostRepository extends JpaRepository<Post, Long>, PagingAndSortingRepository<Post, Long> {

}
