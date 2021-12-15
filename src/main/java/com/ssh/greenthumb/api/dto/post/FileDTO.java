package com.ssh.greenthumb.api.dto.post;

import com.ssh.greenthumb.api.domain.post.File;
import com.ssh.greenthumb.api.domain.post.Post;
import lombok.Getter;

public class FileDTO {

    @Getter
    public static class Create {   // 첨부파일 생성 정보
        private Long postId;
        private String fileUrl;

        public File toEntity(Post post, String fileUrl) {
            return File.builder()
                    .post(post)
                    .fileUrl(fileUrl)
                    .build();
        }

    }

    @Getter
    public static class Get {       // 첨부파일 가져오기
        private Long fileId;
        private Long postId;
    }

    @Getter
    public static class Delete {   // 첨부파일 삭제 정보
        private Post fileId;
    }

}