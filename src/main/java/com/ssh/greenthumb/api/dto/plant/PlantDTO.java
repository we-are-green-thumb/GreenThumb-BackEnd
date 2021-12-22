package com.ssh.greenthumb.api.dto.plant;

import com.ssh.greenthumb.api.domain.plant.Plant;
import com.ssh.greenthumb.api.domain.user.User;
import lombok.Getter;

import java.time.LocalDateTime;

public class PlantDTO {

    @Getter
    public static class Create {    // 식물 작성 필요 정보
        private String name;
        private String nickName;
        private Long water;
        private Long temp;
        private String imageUrl;

        public Plant toEntity(User user, String name, String nickName, Long water,
                              Long temp, String imageUrl) {
            return Plant.builder()
                    .user(user)
                    .name(name)
                    .nickName(nickName)
                    .water(water)
                    .temp(temp)
                    .imageUrl(imageUrl)
                    .build();
        }
    }

    @Getter
    public static class Get {
        private Long plantId;
        private Long userId;
        private String name;
        private String nickName;
        private Long water;
        private Long temp;
        private String imageUrl;
        private LocalDateTime createdDate;

        public Get(Plant entity) {
            this.plantId = entity.getId();
            this.userId = entity.getUser().getId();
            this.name = entity.getName();
            this.nickName = entity.getNickName();
            this.water = entity.getWater();
            this.temp = entity.getTemp();
            this.imageUrl = entity.getImageUrl();
            this.createdDate = entity.getCreatedDate();
        }
    }

    @Getter
    public static class Update {    // 식물정보 수정
        private String name;
        private String nickName;
        private Long water;
        private Long temp;
        private String imageUrl;
    }

}