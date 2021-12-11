package com.ssh.greenthumb.dao.post;

import com.ssh.greenthumb.domain.post.File;
import com.ssh.greenthumb.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {

    // 하나만 찾는 것
    File findFileByFileUrl(String fileUrl);

    // 모두 찾는 것
    List<File> findAllByPostAndFileUrl(Post post, String fileUrl);

    // 파일 삭제
    File deleteByPost(Post post);

}
