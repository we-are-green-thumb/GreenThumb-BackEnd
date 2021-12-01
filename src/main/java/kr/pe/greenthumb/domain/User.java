package kr.pe.greenthumb.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
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

    @OneToMany(mappedBy = "userIdx", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Plant> plantList = new ArrayList<>();

    @OneToMany(mappedBy = "userIdx", cascade = CascadeType.ALL, targetEntity=Board.class)
    @JsonBackReference
    private List<Board> boardList = new ArrayList<>();

    @OneToMany(mappedBy = "userIdx", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Follow> followerList = new ArrayList<>();

    @OneToMany(mappedBy = "following", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Follow> followingList = new ArrayList<>();

}
