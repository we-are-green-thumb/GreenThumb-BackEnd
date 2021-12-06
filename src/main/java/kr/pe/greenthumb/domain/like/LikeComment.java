package kr.pe.greenthumb.domain.like;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import kr.pe.greenthumb.domain.post.Comment;
import kr.pe.greenthumb.domain.user.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LikeComment {

    @Id
    @Column(name = "like_comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeCommentId;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "comment_id")
    @NotNull
    private Comment comment;

    @OneToOne
    @JoinColumn(name = "user_Id")
    @NotNull
    private User user;

    @Builder
    public LikeComment(Comment comment, User user) {
        this.comment = comment;
        this.user = user;
    }

}
