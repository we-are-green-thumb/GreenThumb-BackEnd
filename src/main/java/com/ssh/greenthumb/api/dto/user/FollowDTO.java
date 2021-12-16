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
    public static class Follower {
        private Long followerId;
        private String followerNickName;

        public Follower(Follow entity) {
            this.followerId = entity.getFollower().getId();
            this.followerNickName = entity.getFollower().getNickName();
        }
    }

    @Getter
    public static class Followee {
        private Long followeeId;
        private String followeeNickName;

        public Followee(Follow entity) {
            this.followeeId = entity.getFollowee().getId();
            this.followeeNickName = entity.getFollowee().getNickName();
        }
    }

    //언팔
    @Getter
    public static class Delete {
        private Long followerId;
        private Long followeeId;
    }

}