package kr.pe.greenthumb.dto;

import kr.pe.greenthumb.domain.user.User;
import lombok.Getter;

public class ResponseDTO {

    @Getter
    public static class UserResponse {

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

    }
