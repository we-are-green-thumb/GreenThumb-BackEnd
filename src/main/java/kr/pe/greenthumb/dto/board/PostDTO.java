package kr.pe.greenthumb.dto.board;

import lombok.Data;

public class PostDTO {
    @Data
    public static class Create {   // 게시물 생성 정보
        private Long postIdx;
        private Long userIdx;
        private String Title;
        private String postContent;
        private String postCategory;
        private String postCreateDate;
    }

    @Data
    public static class Get {   // 게시물 가져오는 정보
        private Long postIdx;
        // 나중에 repo에서 따로 만들어주는게 낫다?
//      private String Title;
//      private String postContent;
//      private String postCategory;
//      private Long postHits;
    }

    @Data
    public static class Update {   // 게시물 수정 정보
        private String Title;
        private String postContent;
        private String postCategory;
        private String postUpdateDate;
//      private String postCheck; // DTO로 따로 빼주자
    }

    @Data
    public static class Delete {   // 게시물 삭제 정보
        private Long postIdx;
        private String postDelete;
    }
}