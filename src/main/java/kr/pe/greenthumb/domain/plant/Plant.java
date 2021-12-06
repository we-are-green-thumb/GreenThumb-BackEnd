package kr.pe.greenthumb.domain.plant;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import kr.pe.greenthumb.domain.user.User;
import lombok.*;

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

}