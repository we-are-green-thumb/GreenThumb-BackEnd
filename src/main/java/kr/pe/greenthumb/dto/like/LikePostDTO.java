package kr.pe.greenthumb.dto.like;

import kr.pe.greenthumb.domain.like.LikePost;
import kr.pe.greenthumb.domain.post.Post;
import kr.pe.greenthumb.domain.user.User;
import lombok.Builder;
import lombok.Getter;

public class LikePostDTO {

    @Getter
    public static class Create {
        private Long postIdx;
        private Long userIdx;

        @Builder
        public Create(Long postIdx, Long userIdx) {
            this.postIdx = postIdx;
            this.userIdx = userIdx;
        }

        public LikePost toEntity(Post post, User user) {
            return LikePost.builder()
                    .post(post)
                    .user(user)
                    .build();
        }
    }

    @Getter
    public static class Delete {
        private Long postIdx;
        private Long userIdx;
    }

    @Getter
    public static class Get {
        private Long postIdx;
        private Long userIdx;

        public Get(LikePost entity) {
            this.postIdx = entity.getPost().getPostIdx();
            this.userIdx = entity.getUser().getUserIdx();
        }
    }

}