package kr.pe.greenthumb.domain.post;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import kr.pe.greenthumb.common.domain.BaseTimeEntity;
import kr.pe.greenthumb.domain.like.LikeComment;
import kr.pe.greenthumb.domain.user.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "comment_idx")
    private Long commentIdx;

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

    @JoinColumn(name = "comment_content" ,columnDefinition = "varchar(1500)")
    @NotNull
    private String commentContent;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime commentCreateDate;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime commentUpdateDate;

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
    }

    public Comment update(Long commentIdx, Post post, User user, String commentContent, LocalDateTime commentUpdateDate) {
        this.commentIdx = commentIdx;
        this.post = post;
        this.user = user;
        this.commentContent = commentContent;
        this.commentUpdateDate = commentUpdateDate;
        return this;
    }

    public void delete() {
        this.isDeleted = "y";
    }
}