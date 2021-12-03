package kr.pe.greenthumb.service.like;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import kr.pe.greenthumb.domain.post.Comment;
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
public class LikeCommentService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeCommentIdx;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "comment_idx")
    @NonNull
    private Comment comment;

    @OneToOne
    @JoinColumn(name = "user_Idx")
    @NonNull
    private User user;
}