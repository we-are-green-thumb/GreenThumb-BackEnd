package com.ssh.greenthumb.dto.user;

import com.ssh.greenthumb.domain.user.User;
import lombok.Getter;

import java.time.LocalDateTime;

public class UserDTO {

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
    public static class GetFromAdmin {
        private String email;
        private String nickName;
        private String role;
        private String isDeleted;
        private String deleteReason;
        private LocalDateTime deleteDate;
        private String isBlack;
        private String providerId;

        public GetFromAdmin(User entity) {
            this.email = entity.getEmail();
            this.nickName = entity.getNickName();
            this.role = entity.getRole().getDisplayName();
            this.isDeleted = entity.getIsDeleted();
            this.deleteReason = entity.getDeleteReason();
//        String formatDate = LocalDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            this.deleteDate = entity.getDeleteDate();
            this.isBlack = entity.getIsBlack();
            this.providerId = entity.getProviderId();
        }
    }

    @Getter
    public static class Update {
        private String userPassword;
        private String userNickname;
        private String imageUrl;
    }

}