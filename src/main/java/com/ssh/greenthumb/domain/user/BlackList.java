package com.ssh.greenthumb.domain.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BlackList {

    @Id
    @Column(name = "black_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    @NotNull
    private User user;

    @Column(name = "black_reason", columnDefinition = "varchar(900)")
    @NotNull
    private String reason;

    @Column(name = "black_status")
    @NotNull
    private String status = "y";

    @Builder
    public BlackList(User user, String reason) {
        this.user = user;
        this.reason = reason;
    }

    public BlackList update(String reason) {
        this.reason = reason;
        return this;
    }

    public void delete() {
        this.status = "n";
    }

}