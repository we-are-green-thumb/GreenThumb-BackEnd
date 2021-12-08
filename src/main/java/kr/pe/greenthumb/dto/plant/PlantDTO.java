package kr.pe.greenthumb.dto.plant;

import kr.pe.greenthumb.domain.plant.Plant;
import kr.pe.greenthumb.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class PlantDTO {

    @Getter
    public static class Create {    // 식물 작성 필요 정보
        private Long userId;
        private String plantName;
        private String plantNickname;
        private Long water;
        private Long temp;
        private String imageUrl;

        @Builder
        public Create(Long userId, String plantName, String plantNickname,
                      Long water, Long temp, String imageUrl) {
            this.userId = userId;
            this.plantName = plantName;
            this.plantNickname = plantNickname;
            this.water = water;
            this.temp = temp;
            this.imageUrl = imageUrl;
        }

        public Plant toEntity(User user, String plantName, String plantNickname, Long water,
                              Long temp, String imageUrl) {
            return Plant.builder()
                    .user(user)
                    .plantName(plantName)
                    .plantNickname(plantNickname)
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
        private String plantName;
        private String plantNickname;
        private Long water;
        private Long temp;
        private String imageUrl;

        public Get(Plant entity) {
            this.plantId = entity.getPlantId();
            this.userId = entity.getUser().getUserId();
            this.plantName = entity.getPlantName();
            this.plantNickname = entity.getPlantNickname();
            this.water = entity.getWater();
            this.temp = entity.getTemp();
            this.imageUrl = entity.getImageUrl();
        }
    }

    @Getter
    public static class Update {    // 식물정보 수정
        private String plantName;
        private String plantNickname;
        private Long water;
        private Long temp;
        private String imageUrl;
    }

    @Getter
    public static class Delete {    // 식물 삭제
        private Long plantId;
        private Long userId;
    }

    @Getter
    @Setter
    public static class Request {
        private Long userId;

        public Request(Long userId) {
            this.userId = userId;
        }
    }

}