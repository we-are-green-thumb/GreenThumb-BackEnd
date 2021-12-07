package kr.pe.greenthumb.dto.plant;

import kr.pe.greenthumb.domain.plant.Plant;
import kr.pe.greenthumb.domain.user.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

public class PlantDTO {

    @Getter
    public static class Create {    // 식물 작성 필요 정보
        private Long userId;
        private Long plantId;
        private String plantName;
        private String plantNickname;
        private Long water;
        private Long temp;
        private String imageUrl;

        @Builder
        public Create(Long userId, Long plantId, String plantName, String plantNickname, Long water, Long temp, String imageUrl) {
            this.userId = userId;
            this.plantId = plantId;
            this.plantName = plantName;
            this.plantNickname = plantNickname;
            this.water = water;
            this.temp = temp;
            this.imageUrl = imageUrl;
        }

        public Plant toEntity(User user, Long)
    }

    @Getter
    public static class Get {       // 식물정보 가져오기
        private Long plantId;
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
    }

}