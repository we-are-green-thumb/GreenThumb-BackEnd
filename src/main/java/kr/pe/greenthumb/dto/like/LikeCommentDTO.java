package kr.pe.greenthumb.dto.like;

import lombok.Data;

public class LikeCommentDTO {
    @Data
    public static class Get {
        private Long likeCommentIdx;
        private Long commentIdx;
    }

    @Data
    public static class Create {
        private Long likeCommentIdx;
        private Long commentIdx;
    }

    @Data
    public static class Delete {
        private Long likeCommentIdx;
        private Long commentIdx;
    }
}
