package kr.pe.greenthumb.dto.plant;

import lombok.Data;

import java.time.LocalDateTime;

public class PlantDTO {
    @Data
    public static class Create {    // 식물 작성 필요 정보
        private Long userIdx;
        private Long plantIdx;
        private String plantName;
        private String plantNickname;
        private Long water;
        private Long temp;
        private String imageUrl;
    }

    @Data
    public static class Get {       // 식물정보 가져오기
        private Long plantIdx;
    }

    @Data
    public static class Update {    // 식물정보 수정
        private String plantName;
        private String plantNickname;
        private Long water;
        private Long temp;
        private String imageUrl;
    }

    @Data
    public static class Delete {    // 식물 삭제
        private Long plantIdx;
    }
}
