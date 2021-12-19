package com.ssh.greenthumb.api.dto.like;

import com.ssh.greenthumb.api.domain.like.LikePost;
import com.ssh.greenthumb.api.domain.post.Post;
import com.ssh.greenthumb.api.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LikePostDTO {

    public LikePost toEntity(Post post, User user) {
        return LikePost.builder()
                .post(post)
                .user(user)
                .build();
    }

//    @Getter
//    @AllArgsConstructor
//    public static class Create {
//        private Long postId;
//        private Long userId;
//
//        public LikePost toEntity(Post post, User user) {
//            return LikePost.builder()
//                    .post(post)
//                    .user(user)
//                    .build();
//        }

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
            this.postId = entity.getPost().getId();
            this.userId = entity.getUser().getId();
        }
    }

}