package com.fbc.bot.model.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.OffsetDateTime;

import static java.time.OffsetDateTime.now;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity implements Serializable {

    public static final String SEQUENCE_GENERATOR = "SEQ_GEN";
    public static final int ALLOCATION_SIZE = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_GENERATOR)
    private Long id;

    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    @PreUpdate
    public void preUpdate() {
        updatedAt = now();
    }

    @PrePersist
    public void prePersist() {
        createdAt = now();
        updatedAt = now();
    }
}