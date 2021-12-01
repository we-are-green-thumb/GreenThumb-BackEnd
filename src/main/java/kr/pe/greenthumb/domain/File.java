package kr.pe.greenthumb.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity

public class File {
    @Id
    @Column(name = "file_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long fileIdx;

    @Column(name = "board_idx")
    @NonNull
    private Board boardIdx;

    @Column(name = "file_url")
    @NonNull
    private String fileUrl;

}
