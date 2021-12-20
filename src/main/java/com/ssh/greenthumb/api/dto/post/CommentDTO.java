package com.ssh.greenthumb.api.dto.post;

import com.ssh.greenthumb.api.domain.post.Comment;
import com.ssh.greenthumb.api.domain.post.Post;
import com.ssh.greenthumb.api.domain.user.User;
import lombok.Getter;

public class CommentDTO {

    @Getter
    public static class Create {
        private Long userId;
        private String content;

        public Comment toEntity(Post post, User user, String content) {
            return Comment.builder()
                    .post(post)
                    .user(user)
                    .content(content)
                    .build();
        }
    }

    @Getter
    public static class Get {
        private Long postId;
        private Long writerId;
        private String writer;
        private String content;
        private String isDeleted;
        private int like;

        public Get(Comment entity) {
            this.postId = entity.getPost().getId();
            this.writerId = entity.getUser().getId();
            this.writer = entity.getUser().getNickName();
            this.content = entity.getContent();
            this.isDeleted = entity.getIsDeleted();
            this.like = entity.getLikeCommentList().size();
        }
    }

    @Getter
    public static class Update {
        private String content;
    }

}