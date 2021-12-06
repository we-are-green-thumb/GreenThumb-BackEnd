package kr.pe.greenthumb.domain.plant;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import kr.pe.greenthumb.domain.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Plant {

    @Id
    @Column(name = "plant_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long plantIdx;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user_idx")
    @NonNull
    private User user;

    @Column(name = "plant_name")
    @NonNull
    private String plantName;

    @Column(name = "plant_nickname")
    @NonNull
    private String plantNickname;

    @Column(name = "water")
    @NonNull
    private Long water;

    @Column(name = "temp")
    @NonNull
    private Long temp;

    @Column(name = "image_url")
    @NonNull
    private String imageUrl;

}