package kr.pe.greenthumb.domain;

import lombok.*;
import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "follow_idx")
    private Long followIdx;

    @NonNull
    private Long follower;

    @NonNull
    private Long following;
}
