package com.ssh.greenthumb.api.dto.user;

import com.ssh.greenthumb.api.domain.user.User;
import com.ssh.greenthumb.auth.domain.AuthProvider;
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
    public static class Get {
        private String email;
        private String nickName;
        private String profile;
        private AuthProvider provider;
        private LocalDateTime signUpDate;
        private int followeeCount;
        private int followerCount;

        public Get(User entity) {
            this.email = entity.getEmail();
            this.nickName = entity.getNickName();
            this.profile = entity.getProfile();
            this.provider = entity.getProvider();
            this.signUpDate = entity.getCreatedDate();
            this.followeeCount = entity.getFolloweeList().size();
            this.followerCount = entity.getFollowerList().size();
        }
    }

    @Getter
    public static class Admin {
        private String email;
        private String nickName;
        private String role;
        private String isDeleted;
        private String deleteReason;
        private LocalDateTime deleteDate;
        private String isBlack;
        private AuthProvider provider;

        public Admin(User entity) {
            this.email = entity.getEmail();
            this.nickName = entity.getNickName();
            this.role = entity.getRole().getDisplayName();
            this.isDeleted = entity.getIsDeleted();
            this.deleteReason = entity.getDeleteReason();
            this.deleteDate = entity.getDeleteDate();
            this.isBlack = entity.getIsBlack();
            this.provider = entity.getProvider();
        }
    }

    @Getter
    public static class Update {
        private String nickName;
        private String profile;
    }

    @Getter
    public static class Feed {
        private Long userId;
        private int followeeCount;
        private int followerCount;

        public Feed(User entity) {
            this.userId = entity.getId();
            this.followeeCount = entity.getFolloweeList().size();
            this.followerCount = entity.getFollowerList().size();
        }
    }

}