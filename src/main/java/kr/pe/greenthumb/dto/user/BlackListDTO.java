package kr.pe.greenthumb.dto.user;

import kr.pe.greenthumb.domain.user.BlackList;
import kr.pe.greenthumb.domain.user.User;

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
            this.blackReason = blackReason;
        }

        public BlackList toEntity(User user,String blackReason) {

            return BlackList.builder()
                    .user(user)
                    .blackReason(blackReason)
                    .build();
        }
    }

    @Getter
    public static class Get {       // 블랙리스트 가져오기
        private Long userId;

        public Get(BlackList entity) {
            this.userId = entity.getUser().getUserId();
        }
    }

    @Getter
    public static class Update {    // 블랙리스트 수정
        private Long blackId;
        private String blackReason;
    }

    @Getter
    public static class Delete {    // 블랙리스트 제외
        private Long blackId;
        private Long blackStatus;
    }

}