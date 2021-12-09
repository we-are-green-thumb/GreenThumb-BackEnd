package kr.pe.greenthumb.domain.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import kr.pe.greenthumb.common.domain.BaseTimeEntity;
import kr.pe.greenthumb.domain.plant.Plant;
import kr.pe.greenthumb.domain.post.Comment;
import kr.pe.greenthumb.domain.post.Post;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User extends BaseTimeEntity {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "user_name")
    @NotNull
    private String userName;

    @Column(name = "user_password")
    @NotNull
    private String userPassword;

    @Column(name = "user_nickname")
    @NotNull
    private String userNickname;

    @Column(name = "user_role")
    @NotNull
    private String userRole = "회원";

    @Column(name = "user_delete")
    @NotNull
    private String isDeleted = "n";

    @Column(name = "user_black")
    @NotNull
    private String isBlack = "n";

    // @LastModifiedDate
    @Column(name = "user_delete_date")
    private LocalDateTime userDeleteDate;

    @Column(name = "user_profile", columnDefinition = "varchar(900)")
    private String userProfile;

    @Column(name = "user_delete_reason", columnDefinition = "varchar(900)")
    private String userDeleteReason;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Plant> plantList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Follow> followerList = new HashSet<>();

    @OneToMany(mappedBy = "followee", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Follow> followeeList = new HashSet<>();

    @OneToOne(mappedBy = "user")
    private BlackList blackList;

    //Q 마이페이지에서 유저정보 가져올 때 비밀번호 가져올까?
    @Builder
    public User(String userName, String userPassword, String userNickName) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userNickname = userNickName;
    }

    public User update(String userPassword, String userNickname) {
        this.userPassword = userPassword;
        this.userNickname = userNickname;

        return this;
    }

    public String blackUser() {
        this.isBlack = "y";

        return this.isBlack;
    }

    public String nonBlackUser() {
        this.isBlack = "n";

        return this.isBlack;
    }

    public void delete() { this.isDeleted = "y"; }

}