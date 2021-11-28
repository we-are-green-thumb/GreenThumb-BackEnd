package kr.pe.greenthumb.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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
    @NotNull
    private Long userIdx;

    @JoinColumn(name = "black_reason")
    @NotNull
    private String blackReason;

    @JoinColumn(name = "black_status")
    @NotNull
    private Long blackStatus;
}
