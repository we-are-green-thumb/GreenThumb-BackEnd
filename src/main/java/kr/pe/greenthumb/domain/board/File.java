package kr.pe.greenthumb.domain.board;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@ToString
public class File {
    @Id
    @Column(name = "file_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileIdx;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "board_idx")
    @NonNull
    private Board board;

    @Column(name = "file_url")
    @NonNull
    private String fileUrl;
}
