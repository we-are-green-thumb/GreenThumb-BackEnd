package com.ssh.greenthumb.api.domain.plant;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ssh.greenthumb.api.common.domain.BaseTimeEntity;
import com.ssh.greenthumb.api.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Plant extends BaseTimeEntity {

    @Id
    @Column(name = "plant_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user")
    @NotNull
    private User user;

    @Column(name = "plant_name")
    @NotNull
    private String name;

    @Column(name = "plant_nickname")
    @NotNull
    private String nickName;

    @Column(name = "water")
    @NotNull
    private Long water;

    @Column(name = "temp")
    @NotNull
    private Long temp;

    @Column(name = "image_url")
//    @NotNull
    private String imageUrl;

    @Builder
    public Plant(User user, String name, String nickName,
                 Long water, Long temp, String imageUrl) {
        this.user = user;
        this.name = name;
        this.nickName = nickName;
        this.water = water;
        this.temp = temp;
        this.imageUrl = imageUrl;
    }

    public Plant update(String name, String nickName,
                        Long water, Long temp, String imageUrl) {
        this.name = name;
        this.nickName = nickName;
        this.water = water;
        this.temp = temp;
        this.imageUrl = imageUrl;

        return this;
    }

    public Plant patch(String name) {
        this.name = name;

        return this;
    }

}