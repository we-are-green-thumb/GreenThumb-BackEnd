package kr.pe.greenthumb.domain.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@Builder
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "follow_idx")
    private Long followIdx;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user_follow")
    @NonNull
//    @NotNull
    private User follower;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user_following")
    @NonNull
    private User following;

}