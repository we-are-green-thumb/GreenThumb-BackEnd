package kr.pe.greenthumb.domain;

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
    private long imageIdx;

    @JoinColumn(name = "plant_idx")
    @NonNull
    private Plant plantIdx;

    @Column(name = "image_url")
    @NonNull
    private long imageUrl;


}
