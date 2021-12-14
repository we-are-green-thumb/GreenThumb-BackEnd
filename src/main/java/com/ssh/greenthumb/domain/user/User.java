package com.ssh.greenthumb.domain.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ssh.greenthumb.common.domain.BaseTimeEntity;
import com.ssh.greenthumb.domain.login.AuthProvider;
import com.ssh.greenthumb.domain.login.Role;
import com.ssh.greenthumb.domain.plant.Plant;
import com.ssh.greenthumb.domain.post.Comment;
import com.ssh.greenthumb.domain.post.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Table(name = "user", uniqueConstraints = {
//        @UniqueConstraint(columnNames = "email")
//})
@Entity
public class User extends BaseTimeEntity {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

//    @Column(nullable = false)
//    private String email;

    @Column(name = "user_name")
    @NotNull
    private String userName;

    @Column(name = "user_password")
    @NotNull
    private String userPassword;

    @Column(name = "user_nickname")
//    @NotNull
    private String userNickname;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role = Role.USER;

    @Column(name = "user_delete")
    @NotNull
    private String isDeleted = "n";

    @Column(name = "user_black")
    @NotNull
    private String isBlack = "n";

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider = AuthProvider.LOCAL;

//    @Column
    private String providerId;

    @Column
    private String imageUrl;

//    @Column(nullable = false)
    private Boolean nameVerified = false;

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
    public User(String userName, String userPassword, String userNickName, String imageUrl) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userNickname = userNickName;
        this.imageUrl = imageUrl;
//        this.role = role;
//        this.nameVerified = nameVerified;
//        this.providerId = providerId;

    }

    public User update(String userPassword, String userNickname, String imageUrl) {
        this.userPassword = userPassword;
        this.userNickname = userNickname;
        this.imageUrl = imageUrl;

        return this;
    }

    public String getRoleKey() {
        return this.role.getCode();
    }

    public User updateRole() {
        this.role = Role.ADMIN;

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