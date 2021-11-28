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
//@SequenceGenerator(name="follow_seq", sequenceName="follow_seq", initialValue=1, allocationSize=1)
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "follow_idx")
    private Long followIdx;

    @NotNull
    private Long follower;

    @NotNull
    private Long following;
}
