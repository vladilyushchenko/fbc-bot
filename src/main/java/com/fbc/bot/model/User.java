package com.fbc.bot.model;

import com.fbc.bot.model.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;

import java.util.Objects;

import static com.fbc.bot.model.base.BaseEntity.ALLOCATION_SIZE;
import static com.fbc.bot.model.base.BaseEntity.SEQUENCE_GENERATOR;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@Entity
@Table(name = "users")
@SequenceGenerator(
        name = SEQUENCE_GENERATOR, sequenceName = "SEQ_USER", allocationSize = ALLOCATION_SIZE
)
public class User extends BaseEntity {

    private Long telegramId;

    private String firstName;
    private String lastName;

    private String userName;
    private String nickname;

    @PrimaryKeyJoinColumn
    @OneToOne(mappedBy = "user", cascade = {PERSIST, MERGE}, fetch = LAZY)
    private DailyCockSize cockSize;

    @Enumerated(STRING)
    private UserStatus userStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;

        User user = (User) o;
        if (!Objects.equals(telegramId, user.telegramId)) return false;
        if (!Objects.equals(firstName, user.firstName)) return false;
        if (!Objects.equals(lastName, user.lastName)) return false;
        if (!Objects.equals(userName, user.userName)) return false;
        if (!Objects.equals(nickname, user.nickname)) return false;
        if (!Objects.equals(cockSize, user.cockSize)) return false;
        return userStatus == user.userStatus;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (telegramId != null ? telegramId.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + (cockSize != null ? cockSize.hashCode() : 0);
        result = 31 * result + (userStatus != null ? userStatus.hashCode() : 0);
        return result;
    }
}