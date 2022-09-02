package me.delivery.domain.user.model.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
@Getter
public class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @CreatedDate
    @Column(columnDefinition = "DATETIME", nullable = false)
    protected LocalDateTime createdAt;

}

