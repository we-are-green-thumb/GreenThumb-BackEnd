package kr.pe.greenthumb.dao.post;

import kr.pe.greenthumb.domain.post.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
