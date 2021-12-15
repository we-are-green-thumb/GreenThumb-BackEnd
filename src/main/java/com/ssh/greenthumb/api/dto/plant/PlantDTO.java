package com.ssh.greenthumb.api.dto.plant;

import com.ssh.greenthumb.api.domain.plant.Plant;
import com.ssh.greenthumb.api.domain.user.User;
import lombok.Getter;

public class PlantDTO {

    @Getter
    public static class Create {    // 식물 작성 필요 정보
        private Long userId;
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
    public static class Get {       // 식물정보 가져오기
        private Long plantId;
        private Long userId;
        private String name;
        private String nickName;
        private Long water;
        private Long temp;
        private String imageUrl;

        public Get(Plant entity) {
            this.plantId = entity.getId();
            this.userId = entity.getUser().getId();
            this.name = entity.getName();
            this.nickName = entity.getNickName();
            this.water = entity.getWater();
            this.temp = entity.getTemp();
            this.imageUrl = entity.getImageUrl();
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