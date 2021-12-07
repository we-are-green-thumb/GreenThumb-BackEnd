package kr.pe.greenthumb.domain.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import kr.pe.greenthumb.domain.post.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Follow {

    @Id
    @Column(name = "follow_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followId;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "follower")
    @NotNull
    private kr.pe.greenthumb.domain.post.User follower;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "followee")
    @NotNull
    private User followee;

    @Builder
    public Follow(User follower, User followee) {
        this.follower = follower;
        this.followee = followee;
    }

}