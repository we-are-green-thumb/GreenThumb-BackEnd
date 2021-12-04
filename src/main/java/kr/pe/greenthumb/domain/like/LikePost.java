package kr.pe.greenthumb.domain.like;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import kr.pe.greenthumb.domain.post.Post;
import kr.pe.greenthumb.domain.user.User;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LikePost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likePostIdx;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "post_idx")
    @NotNull
    private Post post;

    @OneToOne
    @JoinColumn(name = "user_Idx")
    @NotNull
    private User user;

    @Builder
    public LikePost(Post post, User user) {
        this.post = post;
        this.user = user;
    }
}
