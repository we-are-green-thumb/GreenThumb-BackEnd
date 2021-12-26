package com.ssh.greenthumb.api.domain.plant;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class Hospital {

    @Id
    private Long id;

    private String disease;
    private String content;
}
