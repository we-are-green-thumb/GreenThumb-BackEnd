package kr.pe.greenthumb.dto.post;

import kr.pe.greenthumb.domain.post.Post;
import kr.pe.greenthumb.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class PostDTO {

    @AllArgsConstructor
    @Getter
    public static class Create {
        private Long userId;
        private String title;
        private String postContent;
        private String postCategory;

        public Post toEntity(User user, String title, String postContent, String postCategory) {
            return Post.builder()
                    .user(user)
                    .title(title)
                    .postContent(postContent)
                    .postCategory(postCategory)
                    .build();
        }
    }

    @Getter
    public static class Get {
        private String title;
        private String postCategory;
        private String postContent;
        private Long postHits;
        private String isComplete;

        public Get(Post entity) {
            this.title = entity.getTitle();
            this.postCategory = entity.getPostCategory();
            this.postContent = entity.getPostContent();
            this.postHits = entity.getPostHits();
            this.isComplete = entity.getIsComplete();
        }
    }

    @Getter
    public static class Update {
        private String title;
        private String postCategory;
        private String postContent;
    }

    @Getter
    public static class UpdateCheck {
        private Long postId;
        private String isComplete;
    }

    @Getter
    public static class Delete {
        private String isDeleted;
    }

}