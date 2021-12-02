package kr.pe.greenthumb.domain.user;

import lombok.*;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@Builder
public class BlackList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "black_idx")
    private Long blackIdx;

    @OneToOne
    @JoinColumn(name = "user_Idx")
    @NonNull
    private User user;

    @Column(name = "black_reason")
    @NonNull
    private String blackReason;

    @Column(name = "black_status")
    @NonNull
    private Long blackStatus;
}