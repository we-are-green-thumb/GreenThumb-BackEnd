package kr.pe.greenthumb.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "comment_idx")
    private Long commentIdx;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name="board_idx")
    @NonNull
    private Board board;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name="user_idx")
    @NonNull
    private User user;

    @JoinColumn(name = "comment_content")
    @NonNull
    private String commentContent;

    @JoinColumn(name = "comment_date")
    @NonNull
    private LocalDate commentDate;

    @JoinColumn(name = "comment_delete")
    @NonNull
    private String commentDelete;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<LikeComment> likeCommentList = new ArrayList<>();


}
