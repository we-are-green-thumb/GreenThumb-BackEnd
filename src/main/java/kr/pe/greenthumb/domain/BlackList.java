package kr.pe.greenthumb.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@Builder
public class BlackList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "black_idx")
    private Long blackIdx;

    @OneToOne
    @JoinColumn(name = "user_Idx")
    @NonNull
    private User user;

    @JoinColumn(name = "black_reason")
    @NonNull
    private String blackReason;

    @JoinColumn(name = "black_status")
    @NonNull
    private Long blackStatus;

}