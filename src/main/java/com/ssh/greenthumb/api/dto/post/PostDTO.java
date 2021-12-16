package com.ssh.greenthumb.api.dto.post;

import com.ssh.greenthumb.api.domain.post.File;
import com.ssh.greenthumb.api.domain.post.Post;
import com.ssh.greenthumb.api.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

public class PostDTO {

    @AllArgsConstructor
    @Getter
    public static class Create {
        private Long userId;
        private String title;
        private String category;
        private String content;
        private List<File> fileList;

        public Post toEntity(User user, String title, String category, String content, List<File> fileList) {
            return Post.builder()
                    .user(user)
                    .title(title)
                    .category(category)
                    .content(content)
                    .fileList(fileList)
                    .build();
        }
    }

    @Getter
    public static class Get {
        private Long id;
        private String writer;
        private String title;
        private String category;
        private String content;
        private Long hits;
        private String isComplete;
        private int like;
        private List<File> fileList;

        public Get(Post entity) {
            this.id = entity.getId();
            this.writer = entity.getUser().getNickName();
            this.title = entity.getTitle();
            this.category = entity.getCategory();
            this.content = entity.getContent();
            this.hits = entity.getHits();
            this.isComplete = entity.getIsComplete();
            this.like = entity.getLikePostList().size();
            this.fileList = entity.getFileList();
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