package com.ssh.greenthumb.api.domain.like;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ssh.greenthumb.api.domain.post.Comment;
import com.ssh.greenthumb.api.domain.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Schema(name = "댓글 좋아요")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LikeComment {

    @Id
    @Column(name = "like_comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "comment")
    @NotNull
    private Comment comment;

    @OneToOne
    @JoinColumn(name = "user")
    @NotNull
    private User user;

    @Builder
    public LikeComment(Comment comment, User user) {
        this.comment = comment;
        this.user = user;
    }

}