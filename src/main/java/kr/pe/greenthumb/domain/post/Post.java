package kr.pe.greenthumb.domain.post;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import kr.pe.greenthumb.common.domain.BaseTimeEntity;
import kr.pe.greenthumb.domain.user.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @Column(name = "post_title", columnDefinition = "varchar(300)")
    @NotNull
    private String title;

    @Column(name = "post_content", columnDefinition = "varchar(1500)")
    @NotNull
    private String postContent;

    @Column(name = "post_category")
    @NotNull
    private String postCategory;

    @Column(name = "post_delete")
    @NotNull
    private String postDelete;

    @Column(name = "post_hits")
    @NotNull
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

    @Builder
    public Post(User user, String title, String postContent, String postCategory) {
        this.user = user;
        this.title = title;
        this.postContent = postContent;
        this.postCategory = postCategory;
    }

    public Post update(String title, String postContent) {
        this.title = title;
        this.postContent = postContent;

        return this;
    }

}