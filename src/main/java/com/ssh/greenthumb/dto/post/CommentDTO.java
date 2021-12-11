package com.ssh.greenthumb.dto.post;

import com.ssh.greenthumb.domain.post.Comment;
import com.ssh.greenthumb.domain.post.Post;
import com.ssh.greenthumb.domain.user.User;
import lombok.Getter;

public class CommentDTO {

    @Getter
    public static class Create {  // 댓글 생성 정보
        private Long postId;
        private Long userId;
        private String commentContent;

        public Comment toEntity(Post post, User user, String commentContent) {
            return Comment.builder()
                    .post(post)
                    .user(user)
                    .commentContent(commentContent)
                    .build();
        }
    }

    @Getter
    public static class Get {
        private Long postId;
        private Long userId;
        private String commentContent;
        private String isDeleted;

        public Get(Comment entity) {
            this.postId = entity.getPost().getPostId();
            this.userId = entity.getUser().getUserId();
            this.commentContent = entity.getCommentContent();
            this.isDeleted = entity.getIsDeleted();
        }
    }

    @Getter
    public static class Update {  // 댓글 수정 정보
        private String commentContent;
    }

    @Getter
    public static class Delete {  // 댓글 삭제 정보
        private String isDeleted;
    }

}