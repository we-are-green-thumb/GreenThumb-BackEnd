package kr.pe.greenthumb.domain.plant;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Image {

    @Id
    @Column(name = "image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @OneToOne
    @JoinColumn(name = "plant_id")
    @NotNull
    private Plant plant;

    @Column(name = "image_url")
    @NotNull
    private String imageUrl;

}
