package kr.pe.greenthumb.domain.like;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import kr.pe.greenthumb.domain.post.Post;
import kr.pe.greenthumb.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LikePost {

    @Id
    @Column(name = "like_post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likePostId;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "post_id")
    @NotNull
    private Post post;

    @OneToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @Builder
    public LikePost(Post post, User user) {
        this.post = post;
        this.user = user;
    }

}
