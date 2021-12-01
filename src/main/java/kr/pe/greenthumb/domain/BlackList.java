package kr.pe.greenthumb.domain;

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
    @JoinColumn(name = "black_idx")
    private Long blackIdx;

    @JoinColumn(name = "user_idx")
    @NonNull
    private Long userIdx;

    @JoinColumn(name = "black_reason")
    @NonNull
    private String blackReason;

    @JoinColumn(name = "black_status")
    @NonNull
    private Long blackStatus;
}
