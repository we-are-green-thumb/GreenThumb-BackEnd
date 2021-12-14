package com.ssh.greenthumb.dto.post;

import com.ssh.greenthumb.domain.post.Post;
import com.ssh.greenthumb.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class PostDTO {

    @AllArgsConstructor
    @Getter
    public static class Create {
        private Long userId;
        private String title;
        private String category;
        private String content;

        public Post toEntity(User user, String title, String category, String content) {
            return Post.builder()
                    .user(user)
                    .title(title)
                    .category(category)
                    .content(content)
                    .build();
        }
    }

    @Getter
    public static class Get {
        private String title;
        private String category;
        private String content;
        private Long hits;
        private String isComplete;

        public Get(Post entity) {
            this.title = entity.getTitle();
            this.category = entity.getCategory();
            this.content = entity.getContent();
            this.hits = entity.getHits();
            this.isComplete = entity.getIsComplete();
        }
    }

    @Getter
    public static class Update {
        private String title;
        private String category;
        private String content;
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