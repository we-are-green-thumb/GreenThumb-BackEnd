package kr.pe.greenthumb.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name="user_idx")
    @NonNull
    private User user;

    @Column(name="plant_name")
    @NonNull
    private String plantName;

    @Column(name="plant_nickname")
    @NonNull
    private String plantNickname;

    @Column(name = "water")
    @NonNull
    private long water;

    @Column(name = "temp")
    @NonNull
    private long temp;

}
