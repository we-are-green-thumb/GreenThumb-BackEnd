package kr.pe.greenthumb.domain;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@SequenceGenerator(name = "")
public class Board {
    @Id
    @Column(name = "board_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long boardIdx;

    @Column(name = "uesr_idx")
    @NonNull
    private long userIdx;

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
