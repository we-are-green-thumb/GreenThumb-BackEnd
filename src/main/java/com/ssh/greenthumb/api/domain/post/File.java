package com.ssh.greenthumb.api.domain.post;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class File {

    @Id
    @Column(name = "file_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "post")
    @NotNull
    private Post post;

    @Column(name = "file_url")
    @NotNull
    private String fileUrl;

    @Builder
    public File(Post post, String fileUrl) {
        this.post = post;
        this.fileUrl = fileUrl;
    }

    public File Create(Post post, String fileUrl) {
        this.post = post;
        this.fileUrl = fileUrl;

        return this;
    }

}