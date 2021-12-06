package kr.pe.greenthumb.domain.post;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import kr.pe.greenthumb.common.domain.BaseTimeEntity;
import kr.pe.greenthumb.domain.like.LikeComment;
import kr.pe.greenthumb.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Comment extends BaseTimeEntity {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "post_idx")
    @NotNull
    private Post post;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user_idx")
    @NotNull
    private User user;

    @JoinColumn(name = "comment_content", columnDefinition = "varchar(1500)")
    @NotNull
    private String commentContent;

    @JoinColumn(name = "comment_delete")
    @NotNull
    private String isDeleted = "n";

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<LikeComment> likeCommentList = new ArrayList<>();

    @Builder
    public Comment(Post post, User user, String commentContent) {
        this.post = post;
        this.user = user;
        this.commentContent = commentContent;
        this.isDeleted = getIsDeleted();
    }

    public Comment update(Long commentIdx, Post post, User user, String commentContent) {
        this.commentId = commentIdx;
        this.post = post;
        this.user = user;
        this.commentContent = commentContent;
        this.isDeleted = getIsDeleted();
        return this;
    }

    public void delete() {
        this.isDeleted = "y";
    }

}