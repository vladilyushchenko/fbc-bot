package com.fbc.bot.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

import static java.time.OffsetDateTime.now;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;

        DailyCockSize cockSize = (DailyCockSize) o;

        if (!Objects.equals(userId, cockSize.userId)) return false;
        if (!Objects.equals(size, cockSize.size)) return false;
        if (!Objects.equals(user, cockSize.user)) return false;
        if (!Objects.equals(createdAt, cockSize.createdAt)) return false;
        return Objects.equals(updatedAt, cockSize.updatedAt);
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (size != null ? size.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }
}