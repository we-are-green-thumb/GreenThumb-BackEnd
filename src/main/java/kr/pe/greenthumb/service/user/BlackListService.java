package kr.pe.greenthumb.service.user;

import lombok.*;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@Builder
public class BlackListService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "black_idx")
    private Long blackIdx;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_Idx")
    @NonNull
    private UserService user;

    @Column(name = "black_reason" ,columnDefinition = "varchar(900)")
    @NonNull
    private String blackReason;

    @Column(name = "black_status")
    @NonNull
    private Long blackStatus;
}