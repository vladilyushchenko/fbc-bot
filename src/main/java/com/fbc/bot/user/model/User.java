package com.fbc.bot.user.model;

import com.fbc.bot.cocksize.model.DailyCockSize;
import com.fbc.bot.common.model.base.BaseEntity;
import com.fbc.bot.music.model.Music;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import static com.fbc.bot.common.model.base.BaseEntity.ALLOCATION_SIZE;
import static com.fbc.bot.common.model.base.BaseEntity.SEQUENCE_GENERATOR;
import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;

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
    private String customName;

    @PrimaryKeyJoinColumn
    @OneToOne(mappedBy = "user", cascade = {PERSIST, MERGE}, fetch = LAZY)
    private DailyCockSize cockSize;

    @Enumerated(STRING)
    private UserStatus userStatus;

    @ManyToMany(mappedBy = "users")
    private Set<Music> music;

}