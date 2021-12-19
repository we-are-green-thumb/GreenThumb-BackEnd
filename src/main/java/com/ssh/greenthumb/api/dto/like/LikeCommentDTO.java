package com.ssh.greenthumb.api.dto.like;

import com.ssh.greenthumb.api.domain.like.LikeComment;
import com.ssh.greenthumb.api.domain.post.Comment;
import com.ssh.greenthumb.api.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class LikeCommentDTO {

    @Getter
    @AllArgsConstructor
    public static class Create {
        private Long commentId;
        private Long userId;

        public LikeComment toEntity(Comment comment, User user) {
            return LikeComment.builder()
                    .comment(comment)
                    .user(user)
                    .build();
        }
    }

    @Getter
    public static class Delete {
        private Long commentId;
        private Long userId;
    }

    @Getter
    public static class Get {
        private Long commentId;
        private Long userId;

        public Get(LikeComment entity) {
            this.commentId = entity.getComment().getId();
            this.userId = entity.getUser().getId();
        }
    }

}