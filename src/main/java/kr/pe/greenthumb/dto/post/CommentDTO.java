package kr.pe.greenthumb.dto.post;

import kr.pe.greenthumb.domain.post.Comment;
import kr.pe.greenthumb.domain.post.Post;
import kr.pe.greenthumb.domain.user.User;
import lombok.Builder;
import lombok.Getter;

public class CommentDTO {

    @Getter
    public static class Create {  // 댓글 생성 정보
        private Long postIdx;
        private Long userIdx;
        private String commentContent;

        @Builder
        public Create(Long postIdx, Long userIdx, String commentContent) {
            this.postIdx = postIdx;
            this.userIdx = userIdx;
            this.commentContent = commentContent;
        }

        public Comment toEntity(Post post, User user) {
            return Comment.builder()
                    .post(post)
                    .user(user)
                    .commentContent(commentContent)
                    .build();
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

    @Getter
    public static class Get {
        private Long postIdx;
        private Long userIdx;
        private String commentContent;
        private String isDeleted;

        public Get(Comment entity) {
            this.postIdx = entity.getPost().getPostIdx();
            this.userIdx = entity.getUser().getUserIdx();
            this.commentContent = entity.getCommentContent();
            this.isDeleted = entity.getIsDeleted();
        }
    }

}