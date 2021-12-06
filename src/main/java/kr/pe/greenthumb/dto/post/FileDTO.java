package kr.pe.greenthumb.dto.post;

import kr.pe.greenthumb.domain.post.Post;
import lombok.Data;

public class FileDTO {

    @Data
    public static class Create {   // 첨부파일 생성 정보
        private Post postIdx;
        private Long fileIdx;
        private String fileUrl;
    }

    @Data
    //첨부파일을 수정하는건 삭제인가 수정인가...?
    public static class Update {   // 첨부파일 수정 정보
        private String fileUrl;
    }

    @Data
    public static class Delete {   // 첨부파일 삭제 정보
        private Post fileIdx;
    }

}