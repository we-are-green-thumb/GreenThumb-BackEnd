package com.ssh.greenthumb.api.dto.post;

import com.ssh.greenthumb.api.domain.post.Post;
import com.ssh.greenthumb.api.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class PostDTO {

    @AllArgsConstructor
    @Getter
    public static class Create {
        private String title;
        private String category;
        private String content;
        private String fileUrl;

        public Post toEntity(User user, String title, String category, String content) {
            return Post.builder()
                    .user(user)
                    .title(title)
                    .category(category)
                    .content(content)
                    .fileUrl(fileUrl)
                    .build();
        }
    }

    @Getter
    @Setter
    public static class Get {
        private Long id;
        private Long writerId;
        private String writer;
        private String title;
        private String category;
        private String content;
        private Long hits;
        private String isComplete;
        private int like;
        private String fileUrl;

        public Get(Post entity) {
            this.id = entity.getId();
            this.writerId = entity.getUser().getId();
            this.writer = entity.getUser().getNickName();
            this.title = entity.getTitle();
            this.category = entity.getCategory();
            this.content = entity.getContent();
            this.hits = entity.getHits();
            this.isComplete = entity.getIsComplete();
            this.like = entity.getLikePostList().size();
            this.fileUrl = entity.getFileUrl();
        }
    }

    @Getter
    public static class Update {
        private String title;
        private String category;
        private String content;
        private String fileUrl;
    }

}