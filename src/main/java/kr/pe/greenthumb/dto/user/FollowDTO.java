package kr.pe.greenthumb.dto.user;

import lombok.Data;

public class FollowDTO {

    //팔로우
    @Data
    public static class Create {
        private Long followId;
        private Long follower;
        private Long followee;
    }

    //언팔
    @Data
    public static class Delete {
        private Long followId;
    }

}