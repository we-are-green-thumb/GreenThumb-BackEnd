package com.ssh.greenthumb.domain.like;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ssh.greenthumb.domain.post.Comment;
import com.ssh.greenthumb.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LikeComment {

    @Id
    @Column(name = "like_comment_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeCommentId;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "comment_id")
    @NotNull
    private Comment comment;

    @OneToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @Builder
    public LikeComment(Comment comment, User user) {
        this.comment = comment;
        this.user = user;
    }

}