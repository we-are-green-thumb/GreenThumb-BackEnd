package kr.pe.greenthumb.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "user_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userIdx;

    @Column(name = "user_email")
    @NonNull
    private String userEmail;

    @Column(name = "user_password")
    @NonNull
    private String userPassword;

    @Column(name = "user_nickname")
    @NonNull
    private String userNickname;

    @Column(name = "user_rights")
    @NonNull
    private String userRights;

    @Column(name = "assign_date")
    @NonNull
    private Date assignDate; // Date로 할 지, String으로 할 지

    @Column(name = "user_out")
    @NonNull
    private String userOut;

    @Column(name = "user_outdate")
    @NonNull
    private Date userOutdate;
}
