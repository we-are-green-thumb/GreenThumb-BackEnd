package com.ssh.greenthumb.api.dto.user;

import com.ssh.greenthumb.api.domain.user.Follow;
import com.ssh.greenthumb.api.domain.user.User;
import lombok.Getter;

public class FollowDTO {

    //팔로우
    @Getter
    public static class Create {
        private Long followerId;
        private Long followeeId;

        public Follow toEntity(User follower, User followee) {
            return Follow.builder()
                    .follower(follower)
                    .followee(followee)
                    .build();
        }
    }

    @Getter
    public static class Get {
        private Long followerId;
        private Long followeeId;

        public Get(Follow entity) {
            this.followerId = entity.getFollower().getId();
            this.followeeId = entity.getFollowee().getId();
        }
    }

    //언팔
    @Getter
    public static class Delete {
        private Long followerId;
        private Long followeeId;
    }

}