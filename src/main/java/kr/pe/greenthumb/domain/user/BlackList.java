package kr.pe.greenthumb.domain.user;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@Builder
public class BlackList {

    @Id
    @Column(name = "black_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blackId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @Column(name = "black_reason", columnDefinition = "varchar(900)")
    @NotNull    private String blackReason;

    @Column(name = "black_status")
    @NotNull
    private Long blackStatus;

}