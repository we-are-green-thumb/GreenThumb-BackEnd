package kr.pe.greenthumb.service.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@Builder
public class FollowService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "follow_idx")
    private Long followIdx;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user_follow")
    @NonNull
//    @NotNull
    private UserService follower;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user_following")
    @NonNull
    private UserService following;
}