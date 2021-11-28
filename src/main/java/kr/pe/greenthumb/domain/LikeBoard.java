package kr.pe.greenthumb.domain;

import lombok.*;
import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LikeBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "board_idx")
    private Long boardIdx;

    @JoinColumn(name = "user_idx")
    @NonNull
    private Long userIdx;
}
