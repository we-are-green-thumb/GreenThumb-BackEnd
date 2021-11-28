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
