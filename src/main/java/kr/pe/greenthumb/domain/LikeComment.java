package kr.pe.greenthumb.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@SequenceGenerator(name="likeBoard_seq", sequenceName="likeBoard_seq", initialValue=1, allocationSize=1)
public class LikeComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "comment_idx")
    private Long commentIdx;

    @JoinColumn(name = "user_idx")
    @NonNull
    private Long userIdx;
}
