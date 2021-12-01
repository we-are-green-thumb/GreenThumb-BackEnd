package kr.pe.greenthumb.domain;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Plant {
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
