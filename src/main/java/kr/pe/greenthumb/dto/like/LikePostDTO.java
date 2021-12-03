package kr.pe.greenthumb.dto.like;

import lombok.Data;

public class LikePostDTO {
    @Data
    public static class Get {
        private Long likePostIdx;
        private Long postIdx;
    }

    @Data
    public static class Create {
        private Long likePostIdx;
        private Long postIdx;
    }

    @Data
    public static class Delete {
        private Long likePostIdx;
        private Long postIdx;
    }
}
