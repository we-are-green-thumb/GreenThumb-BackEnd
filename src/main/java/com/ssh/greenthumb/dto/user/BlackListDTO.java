package com.ssh.greenthumb.dto.user;

import com.ssh.greenthumb.domain.user.BlackList;
import com.ssh.greenthumb.domain.user.User;
import lombok.Getter;

public class BlackListDTO {

    @Getter
    public static class Create {
        private Long userId;
        private String reason;

        public BlackList toEntity(User user, String reason) {
            return BlackList.builder()
                    .user(user)
                    .reason(reason)
                    .build();
        }
    }

    @Getter
    public static class Get {       // 블랙리스트 가져오기
        private Long userId;
        private String nickName;

        public Get(BlackList entity) {
            this.userId = entity.getUser().getId();
            this.nickName = entity.getUser().getNickName();
        }
    }

    @Getter
    public static class Update {    // 블랙리스트 수정
        private Long id;
        private String reason;
    }

    @Getter
    public static class Delete {    // 블랙리스트 제외
        private Long id;
        private Long status;
    }

}