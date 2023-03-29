package com.fbc.bot.user.model;

import com.fbc.bot.cocksize.model.DailyCockSize;
import com.fbc.bot.common.model.base.BaseEntity;
import com.fbc.bot.music.model.Music;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

import static com.fbc.bot.common.model.base.BaseEntity.ALLOCATION_SIZE;
import static com.fbc.bot.common.model.base.BaseEntity.SEQUENCE_GENERATOR;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@Entity
@Table(name = "users")
@EqualsAndHashCode(exclude = {"music", "cockSize"}, callSuper = true)
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

    @ManyToMany(mappedBy = "users")
    private Set<Music> music;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        if (!super.equals(o)) return false;
//        User user = (User) o;
//        return Objects.equals(telegramId, user.telegramId) && Objects.equals(firstName, user.firstName)
//                && Objects.equals(lastName, user.lastName) && Objects.equals(userName, user.userName)
//                && Objects.equals(nickname, user.nickname) && Objects.equals(cockSize, user.cockSize)
//                && userStatus == user.userStatus && Objects.equals(music, user.music);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(super.hashCode(), telegramId, firstName, lastName,
//                userName, nickname, cockSize, userStatus, music);
//    }
}