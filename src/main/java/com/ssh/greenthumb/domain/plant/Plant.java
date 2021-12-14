package com.ssh.greenthumb.domain.plant;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ssh.greenthumb.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Plant {

    @Id
    @Column(name = "plant_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long plantId;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @Column(name = "plant_name")
    @NotNull
    private String plantName;

    @Column(name = "plant_nickname")
    @NotNull
    private String plantNickname;

    @Column(name = "water")
    @NotNull
    private Long water;

    @Column(name = "temp")
    @NotNull
    private Long temp;

    @Column(name = "image_url")
    @NotNull
    private String imageUrl;

    @Builder
    public Plant(User user, String plantName, String plantNickname,
                 Long water, Long temp, String imageUrl) {
        this.user = user;
        this.plantName = plantName;
        this.plantNickname = plantNickname;
        this.water = water;
        this.temp = temp;
        this.imageUrl = imageUrl;
    }

    public Plant update(String plantName, String plantNickname,
                        Long water, Long temp, String imageUrl) {
        this.plantName = plantName;
        this.plantNickname = plantNickname;
        this.water = water;
        this.temp = temp;
        this.imageUrl = imageUrl;

        return this;
    }

    public Plant patch(String plantName) {
        this.plantName = plantName;

        return this;
    }

}