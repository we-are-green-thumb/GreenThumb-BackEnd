package kr.pe.greenthumb.domain;

import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class plant {

    @Id
    @Column(name = "plant_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long plantIdx;

    @Column(name="user_idx")
    @NonNull
    private long userIdx;

    @Column(name="plant_name")
    @NonNull
    private String plantName;

    @Column(name="plant_nickname")
    @NonNull
    private String plantNickname;

    @Column(name = "water")
    @NonNull
    private long water;

}
