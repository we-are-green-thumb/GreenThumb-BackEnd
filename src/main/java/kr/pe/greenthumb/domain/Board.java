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
public class Board {
    @Id
    @Column(name = "board_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long boardIdx;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name="user_idx")
    @NonNull
    private User userIdx;

    @Column(name = "board_title")
    @NonNull
    private String boardTitle;

    @Column(name = "board_content")
    @NonNull
    private String boardContent;

    @Column(name = "board_category")
    @NonNull
    private String boardCategory;

    @Column(name = "board_delete")
    @NonNull
    private String boardDelete;

    @Column(name = "board_hits")
    @NonNull
    private long boardHits;

    @Column(name = "board_complete")
    private long boardComplete;
}
