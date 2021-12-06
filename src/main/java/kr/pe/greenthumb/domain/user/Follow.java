package kr.pe.greenthumb.domain.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@Builder
public class Follow {

    @Id
    @Column(name = "follow_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followId;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user_follow")
    @NotNull
    private User follower;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user_following")
    @NotNull
    private User following;

}