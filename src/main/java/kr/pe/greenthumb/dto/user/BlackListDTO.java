package kr.pe.greenthumb.dto.user;

import lombok.Data;

import java.time.LocalDateTime;

public class BlackListDTO {
    @Data
    public static class Create {    // 블랙리스트 생성 정보
        private Long blackIdx;
        private Long userIdx;
        private String blackReason;
        private Long blackStatus;
    }

    @Data
    public static class Get {       // 블랙리스트 가져오기
        private Long blackIdx;
    }

    @Data
    public static class Update {    // 블랙리스트 수정
        private String blackReason;
        private Long blackStatus;
    }

    @Data
    public static class Delete {    // 블랙리스트 제외
        private Long blackIdx;
        private Long blackStatus;
    }
}
