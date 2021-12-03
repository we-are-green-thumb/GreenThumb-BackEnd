package kr.pe.greenthumb.service.plant;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class ImageService {
    @Id
    @Column(name = "image_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageIdx;

    @OneToOne
    @JoinColumn(name = "plant_idx")
    @NonNull
    private PlantService plant;

    @Column(name = "image_url")
    @NonNull
    private String imageUrl;

}
