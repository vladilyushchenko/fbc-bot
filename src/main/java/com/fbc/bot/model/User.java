package com.fbc.bot.model;

import com.fbc.bot.model.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

    private Integer telegramId;

    private String firstName;
    private String lastName;
    private String userName;
    private String nickname;

    @PrimaryKeyJoinColumn
    @OneToOne(mappedBy = "user", cascade = {PERSIST, MERGE}, fetch = LAZY)
    private DailyCockSize cockSize;

    @Enumerated(STRING)
    private UserStatus userStatus;
}