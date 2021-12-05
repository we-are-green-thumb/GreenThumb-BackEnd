package kr.pe.greenthumb.common.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedDate;

//    @PrePersist
//    public void before() {
//        LocalDateTime now = LocalDateTime.now();
//        this.createdTime = now;
//        this.modifiedTime = now;
//    }
//
//    @PreUpdate
//    public void always() {
//        this.modifiedTime = LocalDateTime.now();
//    }
}
