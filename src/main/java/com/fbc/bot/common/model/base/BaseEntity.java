package com.fbc.bot.common.model.base;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;

import static java.time.OffsetDateTime.now;

@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode
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