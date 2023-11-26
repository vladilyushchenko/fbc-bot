package com.fbc.bot.cocksize.model;

import com.fbc.bot.user.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.OffsetDateTime;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.AUTO;
import static java.time.OffsetDateTime.now;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "user")
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