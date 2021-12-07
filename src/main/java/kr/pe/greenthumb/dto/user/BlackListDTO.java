package kr.pe.greenthumb.dto.user;

import kr.pe.greenthumb.domain.post.Post;
import kr.pe.greenthumb.domain.user.BlackList;
import kr.pe.greenthumb.domain.post.User;
import lombok.Builder;
import lombok.Getter;

public class BlackListDTO {

    @Getter
    public static class Create {    // 블랙리스트 생성 정보
        private Long userId;
        private Long blackId;
        private String blackReason;

        @Builder
        public Create(Long userId, Long blackId, String blackReason) {
            this.userId = userId;
            this.blackId = blackId;
            this.blackReason = blackReason;
        }

        public BlackList toEntity(User user, BlackList blackList) {
            return BlackList.builder()
                    .user(user)
                    .blackReason(blackReason)
                    .build();
        }
    }

    @Getter
    public static class Get {       // 블랙리스트 가져오기
        private Long blackId;
    }

    @Getter
    public static class Update {    // 블랙리스트 수정
        private String blackReason;
        private String blackStatus;
    }

    @Getter
    public static class Delete {    // 블랙리스트 제외
        private Long blackId;
        private Long blackStatus;
    }

}
