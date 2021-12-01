package kr.pe.greenthumb.domain;

import lombok.*;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
//@Builder
public class LikeComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "comment_idx")
    private Long commentIdx;

    @JoinColumn(name = "user_idx")
    @NonNull
    private Long userIdx;
}
