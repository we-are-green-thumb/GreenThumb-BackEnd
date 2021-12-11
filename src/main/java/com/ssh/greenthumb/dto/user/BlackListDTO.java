package com.ssh.greenthumb.dto.user;

import com.ssh.greenthumb.domain.user.BlackList;
import com.ssh.greenthumb.domain.user.User;
import lombok.Getter;

public class BlackListDTO {

    @Getter
    public static class Create {
        private Long userId;
        private String blackReason;

        public BlackList toEntity(User user, String blackReason) {
            return BlackList.builder()
                    .user(user)
                    .blackReason(blackReason)
                    .build();
        }
    }

    @Getter
    public static class Get {       // 블랙리스트 가져오기
        private Long userId;
        private String userNickname;

        public Get(BlackList entity) {
            this.userId = entity.getUser().getUserId();
            this.userNickname = entity.getUser().getUserNickname();
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