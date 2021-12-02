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
public class LikeComment {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @JoinColumn(name = "comment_idx")
//    private Long commentIdx;
//
//    @JoinColumn(name = "user_idx")
//    @NonNull
//    private Long userIdx;
//

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeCommentIdx;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name="comment_idx")
    @NonNull
    private Comment comment;

    @OneToOne
    @JoinColumn(name = "user_Idx")
    @NonNull
    private User user;
}
