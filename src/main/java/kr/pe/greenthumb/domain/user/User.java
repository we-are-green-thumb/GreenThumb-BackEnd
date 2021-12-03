package kr.pe.greenthumb.domain.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import kr.pe.greenthumb.domain.board.Post;
import kr.pe.greenthumb.domain.board.Comment;
import kr.pe.greenthumb.domain.plant.Plant;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    @Id
    @Column(name = "user_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userIdx;

    @Column(name = "user_email")
    @NonNull
    @Email
    private String userEmail;

    @Column(name = "user_password")
    @NonNull
    private String userPassword;

    @Column(name = "user_nickname")
    @NonNull
    private String userNickname;

    @Column(name = "user_role")
    @NonNull
    private String userRole;

    @Column(name = "assign_date")
    @NonNull
    private LocalDateTime assignDate; // Date로 할 지, String으로 할 지

    @Column(name = "user_delete")
    @NonNull
    private String userDeleteCheck;

    @CreatedDate
    @Column(name = "user_delete_date")
    private LocalDateTime userDeleteDate;

    @Column(name = "user_delete_reason" , columnDefinition = "varchar(900)" )
    private String userDeleteReason;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Plant> plantList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Follow> followerList = new HashSet<>();

    @OneToMany(mappedBy = "following", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Follow> followingList = new HashSet<>();

    @OneToOne(mappedBy = "user")
    private BlackList blackList;
}