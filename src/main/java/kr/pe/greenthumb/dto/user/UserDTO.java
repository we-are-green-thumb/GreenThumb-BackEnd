package kr.pe.greenthumb.dto.user;

import kr.pe.greenthumb.domain.user.User;
import lombok.Getter;

public class UserDTO {

    @Getter   // (실무에서) '@Data' 사용을 지양하라고 하던데 이유는?, 일단 '@Data' 만듭니다!
    public static class Login {     // 로그인 정보
        private String userName;
        private String userPassword;
    }

    @Getter
    public static class Create {    // 회원가입 필요 정보
        private String userName;
        private String userPassword;
        private String userNickname;

        public User toEntity(String userName, String userPassword, String userNickname) {
            return User.builder()
                    .userName(userName)
                    .userPassword(userPassword)
                    .userNickName(userNickname)
                    .build();
        }
    }

    @Getter
    public static class Get {
        private String userName;
        private String userPassword;
        private String userNickname;
        private String userProfile;

        public Get(User entity) {
            this.userName = entity.getUserName();
            this.userPassword = entity.getUserPassword();
            this.userNickname = entity.getUserNickname();
            this.userProfile = entity.getUserProfile();
        }
    }

    @Getter
    public static class Update {
        private String userPassword;
        private String userNickname;
    }

    @Getter
    public static class Delete {    // 회원 탈퇴 - 시간 넣기!
        private String isDeleted;
    }

}