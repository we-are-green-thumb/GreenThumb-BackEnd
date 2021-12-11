package com.ssh.greenthumb.dto.like;

import com.ssh.greenthumb.domain.like.LikePost;
import com.ssh.greenthumb.domain.post.Post;
import com.ssh.greenthumb.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class LikePostDTO {

    @Getter
    @AllArgsConstructor
    public static class Create {
        private Long postId;
        private Long userId;

        public LikePost toEntity(Post post, User user) {
            return LikePost.builder()
                    .post(post)
                    .user(user)
                    .build();
        }
    }

    @Getter
    public static class Delete {
        private Long postId;
        private Long userId;
    }

    @Getter
    public static class Get {
        private Long postId;
        private Long userId;

        public Get(LikePost entity) {
            this.postId = entity.getPost().getPostId();
            this.userId = entity.getUser().getUserId();
        }
    }

}