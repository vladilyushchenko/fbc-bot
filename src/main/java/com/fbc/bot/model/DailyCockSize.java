package com.fbc.bot.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;

import static java.time.OffsetDateTime.now;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "daily_cock_sizes")
public class DailyCockSize implements Serializable {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long userId;

    private Long size;

    @MapsId
    @OneToOne(fetch = LAZY)
    private User user;

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