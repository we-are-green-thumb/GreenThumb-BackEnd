package kr.pe.greenthumb.domain.plant;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Image {

    @Id
    @Column(name = "image_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageIdx;

    @OneToOne
    @JoinColumn(name = "plant_idx")
    @NonNull
    private Plant plant;

    @Column(name = "image_url")
    @NonNull
    private String imageUrl;

}
