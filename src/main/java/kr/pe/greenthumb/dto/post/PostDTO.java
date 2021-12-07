package kr.pe.greenthumb.dto.post;

import kr.pe.greenthumb.domain.post.Post;
import kr.pe.greenthumb.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class PostDTO {

    @AllArgsConstructor
    @Getter
    public static class Create {   // 게시물 생성 정보
        private Long userId;
        private String title;
        private String postContent;
        private String postCategory;

        public Post toEntity(User user) {
            return Post.builder()
                    .user(user)
                    .title(title)
                    .postContent(postContent)
                    .postCategory(postCategory)
                    .build();
        }
    }

    @Getter
    public static class Update {   // 게시물 수정 정보
        private String title;
        private String postContent;
        private String postCategory;

//        public Post toEntity() {
//            return Post.builder()
//                    .title(title)
//                    .postContent(postContent)
//                    .postCategory(postCategory)
//                    .build();
//        }
    }

    @Builder
    public static class Get {
        private String title;
        private String postCategory;
        private String postContent;
        private Long postHits;
        private String postCheck;
    }

    // 질문완료 or 거래완료 상태 변경
    @Getter
    public static class PostCheckUpdate {
        private String postCheck;

        public void PostCheckUpdate(Post entity) {
            this.postCheck = entity.getPostCheck();
        }
    }

    @Getter
    public static class Delete {   // 게시물 삭제 정보
        private Long postId;
        private String postDelete;
    }

    @Getter
    public static class UpdateCheck {
        private String updateCheck;
    }

}