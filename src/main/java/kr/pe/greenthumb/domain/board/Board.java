package kr.pe.greenthumb.domain.board;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import kr.pe.greenthumb.domain.login.BaseTimeEntity;
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
public class Board extends BaseTimeEntity {
    @Id
    @Column(name = "board_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardIdx;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user_idx")
    @NonNull
    private User user;

    @Column(name = "board_title")
    @NonNull
    private String boardTitle;

    @Column(name = "board_content")
    @NonNull
    private String boardContent;

    @Column(name = "board_category")
    @NonNull
    private String boardCategory;

    @Column(name = "board_delete")
    @NonNull
    private String boardDelete;

    @Column(name = "board_hits")
    @NonNull
    private Long boardHits;

    // 자유게시판을 제외한 질문, 거래 게시판 완료 여부 체크
    @Column(name = "board_check")
    private String boardCheck;

    @CreatedDate // insert시에 자동으로 들어갈 것으로 추측
    @Column(name = "board_create")
    private LocalDateTime boardCreateDate;

    @LastModifiedDate // update시에 자동으로 들어갈 것으로 추측
    @Column(name = "board_update")
    private LocalDateTime boardUpdateDate;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<File> fileList = new ArrayList<>();
}