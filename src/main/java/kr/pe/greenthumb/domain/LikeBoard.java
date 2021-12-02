package kr.pe.greenthumb.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
//@Builder
public class LikeBoard {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @JoinColumn(name = "board_idx")
//    private Long boardIdx;
//
//    @JoinColumn(name = "user_idx")
//    @NonNull
//    private Long userIdx;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardLikeIdx;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "board_idx")
    @NonNull
    private Board board;

    @OneToOne
    @JoinColumn(name = "user_Idx")
    @NonNull
    private User user;
}
