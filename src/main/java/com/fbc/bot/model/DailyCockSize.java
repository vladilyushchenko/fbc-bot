package com.fbc.bot.model;

import com.fbc.bot.model.base.BaseEntity;
import lombok.*;

import javax.persistence.*;

import java.io.Serializable;
import java.time.OffsetDateTime;

import static java.time.OffsetDateTime.now;
import static javax.persistence.GenerationType.AUTO;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "daily_cock_sizes")
public class DailyCockSize implements Serializable {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long userId;

    private Long size;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
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