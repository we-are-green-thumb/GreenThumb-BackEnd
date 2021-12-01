package kr.pe.greenthumb.domain;

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
    @JoinColumn(name = "follower_user_idx")
    @NonNull
    private User follower;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "following_user_idx")
    @NonNull
    private User following;
}
