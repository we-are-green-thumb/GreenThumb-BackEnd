package kr.pe.greenthumb.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@SequenceGenerator(name="comment_seq", sequenceName="comment_seq", initialValue=1, allocationSize=1)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "comment_idx")
    private Long commentIdx;

    @JoinColumn(name = "board_idx")
    @NotNull
    private Long boardIdx;

    @JoinColumn(name = "user_idx")
    @NotNull
    private Long userIdx;

    @JoinColumn(name = "comment_content")
    @NotNull
    private String commentContent;

    @JoinColumn(name = "comment_date")
    @NotNull
    private LocalDate commentDate;

    @JoinColumn(name = "comment_delete")
    @NotNull
    private String commentDelete;
}
