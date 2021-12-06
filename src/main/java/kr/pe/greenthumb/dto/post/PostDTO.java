package kr.pe.greenthumb.dto.post;

import kr.pe.greenthumb.domain.post.Post;
import kr.pe.greenthumb.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class PostDTO {

    @AllArgsConstructor
    @Getter
    public static class Create {   // 게시물 생성 정보
        private Long userId;
        private String title;
        private String postContent;
        private String postCategory;
//        private String postCreateDate;    // 날짜 자동이라 안 넣어줘도 되는 것으로 추정,,

        // 모든 멤버변수를 파라미터로 받기 때문에 굳이 Builder로 따로 생성자 만들 필요 없음 (?)
        // 그래서 위에 AllArgs... 선언
//        @Builder
//        public Create(Long userIdx, String title, String postContent, String postCategory) {
//            this.userIdx = userIdx;
//            this.title = title;
//            this.postContent = postContent;
//            this.postCategory = postCategory;
//        }

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
    public static class Get {   // 게시물 가져오는 정보
        private Long postId;
        // 나중에 repo에서 따로 만들어주는게 낫다?
//      private String Title;
//      private String postContent;
//      private String postCategory;
//      private Long postHits;
    }

    @Getter
    public static class Update {   // 게시물 수정 정보
        private String Title;
        private String postContent;
        private String postCategory;
        private String postUpdateDate;
//      private String postCheck; // DTO로 따로 빼주자
    }

    @Getter
    public static class Delete {   // 게시물 삭제 정보
        private Long postId;
        private String postDelete;
    }

    @Getter
    public static class Check {
        private String postCheck;
    }

}