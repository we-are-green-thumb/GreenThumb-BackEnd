package kr.pe.greenthumb.dto.like;

import kr.pe.greenthumb.domain.like.LikeComment;
import kr.pe.greenthumb.domain.post.Comment;
import kr.pe.greenthumb.domain.user.User;
import lombok.Builder;
import lombok.Getter;

public class LikeCommentDTO {

    @Getter
    public static class Create {
        private Long commentIdx;
        private Long userIdx;

        @Builder
        public Create(Long commentIdx, Long userIdx) {
            this.commentIdx = commentIdx;
            this.userIdx = userIdx;
        }

        public LikeComment toEntity(Comment comment, User user) {
            return LikeComment.builder()
                    .comment(comment)
                    .user(user)
                    .build();
        }
    }

    @Getter
    public static class Delete {
        private Long commentIdx;
        private Long userIdx;
    }

    @Getter
    public static class Get {
        private Long commentIdx;
        private Long userIdx;

        public Get(LikeComment entity) {
            this.commentIdx = entity.getComment().getCommentIdx();
            this.userIdx = entity.getUser().getUserIdx();
        }
    }

}