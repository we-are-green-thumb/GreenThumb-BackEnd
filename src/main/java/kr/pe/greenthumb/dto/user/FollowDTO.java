package kr.pe.greenthumb.dto.user;

import kr.pe.greenthumb.domain.user.Follow;
import kr.pe.greenthumb.domain.user.User;
import lombok.Builder;
import lombok.Getter;

public class FollowDTO {

    //팔로우
    @Getter
    public static class Create {
        private Long followerId;
        private Long followeeId;

        @Builder
        public Create(Long followerId, Long followeeId) {
            this.followerId = followerId;
            this.followeeId = followeeId;
        }

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
            this.followerId = entity.getFollower().getUserId();
            this.followeeId = entity.getFollowee().getUserId();
        }
    }

    //언팔
    @Getter
    public static class Delete {
        private Long followerId;
        private Long followeeId;
    }

}