package kr.pe.greenthumb.domain.board;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class File {
    @Id
    @Column(name = "file_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileIdx;

    @ManyToOne
<<<<<<< HEAD:src/main/java/kr/pe/greenthumb/domain/File.java
=======
    @JsonManagedReference
>>>>>>> fa7609331316d3400909b891e805d5999a923113:src/main/java/kr/pe/greenthumb/domain/board/File.java
    @JoinColumn(name = "board_idx")
    @NonNull
    private Board board;

    @Column(name = "file_url")
    @NonNull
    private String fileUrl;
}
