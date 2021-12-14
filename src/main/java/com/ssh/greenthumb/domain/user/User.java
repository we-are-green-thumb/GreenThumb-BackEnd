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
@Entity
public class User extends BaseTimeEntity {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
//    @NotNull
    private String email;

    @Column(name = "user_password")
//    @NotNull
    private String password;

    @Column(name = "user_nickname")
    @NotNull
    private String nickName;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role = Role.USER;

    @Column(name = "user_delete")
    @NotNull
    private String isDeleted = "n";

    @Column(name = "user_black")
    @NotNull
    private String isBlack = "n";

    @Enumerated(EnumType.STRING)
    @NotNull
    private AuthProvider provider = AuthProvider.local;

    @Column
    private String providerId;

    @Column
    private String imageUrl;

    @NotNull
    private Boolean emailVerified = false;

    // @LastModifiedDate
    @Column(name = "user_delete_date")
    private LocalDateTime deleteDate;

    @Column(name = "user_profile", columnDefinition = "varchar(900)")
    private String profile;

    @Column(name = "user_delete_reason", columnDefinition = "varchar(900)")
    private String deleteReason;

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
    public User(String email, String password, String nickName, String imageUrl, Role role, AuthProvider provider, String providerId) {
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.imageUrl = imageUrl;
        this.role = Role.USER;
        this.provider = AuthProvider.local;
        this.providerId = providerId;

    }

    public User update(String password, String nickName, String imageUrl) {
        this.password = password;
        this.nickName = nickName;
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
        this.role = Role.BLACK;

        return this.isBlack;
    }

    public String nonBlackUser() {
        this.isBlack = "n";

        return this.isBlack;
    }

    public void delete() {
        this.isDeleted = "y";
    }

}