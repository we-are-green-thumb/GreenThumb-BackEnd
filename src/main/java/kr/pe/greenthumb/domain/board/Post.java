package kr.pe.greenthumb.domain.board;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import kr.pe.greenthumb.domain.user.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
//import javax.validation.constraints.NotNull;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Post extends BaseTimeEntity {
    @Id
    @Column(name = "post_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postIdx;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user_idx")
    @NonNull
    private User user;

    @Column(name = "post_title")
    @NonNull
    private String Title;

    @Column(name = "post_content")
    @NonNull
    private String postContent;

    @Column(name = "post_category")
    @NonNull
    private String postCategory;

    @CreatedDate
    @JoinColumn(name = "post_create")
    @NonNull
    private LocalDateTime postCreateDate;
    @LastModifiedDate
    @JoinColumn(name = "post_update")
    @NonNull
    private LocalDateTime postUpdateDate;

    @Column(name = "post_delete")
    @NonNull
    private String postDelete;

    @Column(name = "post_hits")
    @NonNull
    private Long postHits;

    // 자유게시판을 제외한 질문, 거래 게시판 완료 여부 체크
    @Column(name = "post_check")
    private String postCheck;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<File> fileList = new ArrayList<>();
}