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
        private Long followingId;

        @Builder
        public Create(Long followerId, Long followingId) {
            this.followerId = followerId;
            this.followingId = followingId;
        }

        public Follow toEntity(User follower, User following) {
            return Follow.builder()
                    .follower(follower)
                    .following(following)
                    .build();
        }
    }

    @Getter
    public static class Get {
        private Long followerId;
        private Long followingId;

        public Get(Follow entity) {
            this.followerId = entity.getFollower().getUserId();
            this.followingId = entity.getFollowing().getUserId();
        }
    }

    //언팔
    @Getter
    public static class Delete {
        private Long followerId;
        private Long followingId;
    }

}