package kr.pe.greenthumb.dto.post;

import kr.pe.greenthumb.domain.post.File;
import kr.pe.greenthumb.domain.post.Post;
import lombok.Builder;
import lombok.Getter;

public class FileDTO {

    @Getter
    public static class Create {   // 첨부파일 생성 정보
        private Long postId;
        private Long fileId;
        private String fileUrl;

        @Builder
        public Create(Long postId, String fileUrl) {
            this.postId = postId;
            this.fileUrl = fileUrl;
        }

        public File toEntity(Post post, String fileUrl) {
            return File.builder()
                    .post(post)
                    .fileUrl(fileUrl)
                    .build();
        }

    }

    @Getter
    public static class Update {   // 첨부파일 수정 정보
        private String fileUrl;
    }

    @Getter
    public static class Delete {   // 첨부파일 삭제 정보
        private Post fileId;
    }

}