package com.ssh.greenthumb.dto.user;

import com.ssh.greenthumb.domain.user.User;
import lombok.Getter;

public class UserDTO {

//    @Getter   // (실무에서) '@Data' 사용을 지양하라고 하던데 이유는?, 일단 '@Data' 만듭니다!
//    public static class Login {     // 로그인 정보
//        private String userName;
//        private String userPassword;
//    }

    @Getter
    public static class Create {    // 회원가입 필요 정보
        private String email;
        private String password;
        private String nickName;
        private String imageUrl;
        private String providerId;

        public User toEntity(String email, String password, String nickName, String imageUrl, String providerId) {
            return User.builder()
                    .email(email)
                    .password(password)
                    .nickName(nickName)
                    .imageUrl(imageUrl)
                    .providerId(providerId)
                    .build();
        }
    }

    @Getter
    public static class CheckEmail {
        private String email;
    }

    @Getter
    public static class CheckNickName {
        private String nickName;
    }

    @Getter
    public static class Get {
        private String email;
        // Q 마이페이지에서 유저 정보 가져올 떄 패스워드 필요성 여부
        private String password;
        private String nickName;

        public Get(User entity) {
            this.email = entity.getEmail();
            this.password = entity.getPassword();
            this.nickName = entity.getNickName();
        }
    }

    @Getter
    public static class Update {
        private String userPassword;
        private String userNickname;
        private String imageUrl;
    }

}