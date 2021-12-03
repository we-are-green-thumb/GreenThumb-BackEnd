package kr.pe.greenthumb.domain.post;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import kr.pe.greenthumb.domain.BaseTimeEntity;
import kr.pe.greenthumb.domain.like.LikeComment;
import kr.pe.greenthumb.domain.user.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
//@Builder
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "comment_idx")
    private Long commentIdx;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "post_idx")
    @NonNull
    private Post post;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user_idx")
    @NonNull
    private User user;

    @JoinColumn(name = "comment_content" ,columnDefinition = "varchar(1500)")
    @NonNull
    private String commentContent;

    @CreatedDate
    @JoinColumn(name = "comment_create")
    @NonNull
    private LocalDateTime commentCreateDate;

    @LastModifiedDate
    @JoinColumn(name = "comment_update")
    private LocalDateTime commentUpdateDate;

    @JoinColumn(name = "comment_delete")
    @NonNull
    private String commentDelete;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<LikeComment> likeCommentList = new ArrayList<>();
}