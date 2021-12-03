package kr.pe.greenthumb.dto.board;

import kr.pe.greenthumb.domain.post.Post;
import kr.pe.greenthumb.domain.user.User;
import lombok.Data;

import java.time.LocalDateTime;

public class CommentDTO {
    @Data
    public static class Create {   // 댓글 생성 정보
        private Post postIdx;
        private User userIdx;
        private Long commentIdx;
        private String commentContent;
        private LocalDateTime commentCreateDate;
    }

    @Data
    public static class Update {   // 댓글 수정 정보
        private Long commentIdx;
        private String commentContent;
        private LocalDateTime commentUpdateDate;
    }

    @Data
    public static class Delete {   // 댓글 삭제 정보
        private Long commentIdx;
        private String commentDelete;
    }
}
