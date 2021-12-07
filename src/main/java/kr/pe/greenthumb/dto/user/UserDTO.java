package kr.pe.greenthumb.dto.user;

import lombok.Getter;

import java.time.LocalDateTime;

public class UserDTO {

    @Getter   // (실무에서) '@Data' 사용을 지양하라고 하던데 이유는?, 일단 '@Data' 만듭니다!
    public static class Login {     // 로그인 정보
        private String userName;
        private String userPassword;
    }

    @Getter
    public static class Create {    // 회원가입 필요 정보
        private String userName;
        private String userPassword;
        private String userNickname;
    }

    @Getter
    public static class Get {       // 회원정보 가져오기
        private Long userId;
        // 나중에 repo에서 따로 만들어주는게 낫다?
//      private String userName;
//      private String userPassword;
//      private String userNickname;
    }

    @Getter
    public static class Update {    // 회원정보 수정
        private Long userId;
        private String userPassword;
        private String userNickname;
    }

    @Getter
    public static class Delete {    // 회원 탈퇴
        private Long userId;
        private LocalDateTime userDeleteDate;
        private String userDeleteReason;
    }

}