package com.ssh.greenthumb.api.dto.post;

import com.ssh.greenthumb.api.domain.post.Comment;
import com.ssh.greenthumb.api.domain.post.Post;
import com.ssh.greenthumb.api.domain.user.User;
import lombok.Getter;

public class CommentDTO {

    @Getter
    public static class Create {  // 댓글 생성
        private Long postId;
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
    public static class Get {   // 댓글 정보
        private Long postId;
        private Long userId;
        private String commentContent;
        private String isDeleted;

        public Get(Comment entity) {
            this.postId = entity.getPost().getId();
            this.userId = entity.getUser().getId();
            this.commentContent = entity.getContent();
            this.isDeleted = entity.getIsDeleted();
        }
    }

    @Getter
    public static class Update {  // 댓글 수정 정보
        private String content;
    }

    @Getter
    public static class Delete {  // 댓글 삭제 정보
        private String isDeleted;
    }

}