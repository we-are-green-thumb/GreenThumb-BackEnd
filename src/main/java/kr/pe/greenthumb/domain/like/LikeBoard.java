package kr.pe.greenthumb.domain.like;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import kr.pe.greenthumb.domain.board.Board;
import kr.pe.greenthumb.domain.user.User;
import lombok.*;
import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
//@Builder
public class LikeBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeBoardIdx;

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
