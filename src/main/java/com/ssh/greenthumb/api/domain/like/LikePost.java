package com.ssh.greenthumb.api.domain.like;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ssh.greenthumb.api.domain.user.User;
import com.ssh.greenthumb.api.domain.post.Post;
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
    private Long id;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "post")
    @NotNull
    private Post post;

    @OneToOne
    @JoinColumn(name = "user")
    @NotNull
    private User user;

    @Builder
    public LikePost(Post post, User user) {
        this.post = post;
        this.user = user;
    }

}
