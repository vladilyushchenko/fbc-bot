package com.fbc.bot.music.model;

import com.fbc.bot.common.model.base.BaseEntity;
import com.fbc.bot.user.model.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Set;

import static com.fbc.bot.common.model.base.BaseEntity.ALLOCATION_SIZE;
import static com.fbc.bot.common.model.base.BaseEntity.SEQUENCE_GENERATOR;
import static jakarta.persistence.EnumType.STRING;

@Getter
@Setter
@Entity
@Table(name = "music")
@EqualsAndHashCode(exclude = "users", callSuper = true)
@SequenceGenerator(
        name = SEQUENCE_GENERATOR, sequenceName = "SEQ_MUSIC", allocationSize = ALLOCATION_SIZE
)
public class Music extends BaseEntity {

    private String title;

    private String author;
    private String link;

    @Enumerated(STRING)
    private DataSource dataSource;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(name = "users_music",
            joinColumns = @JoinColumn(name = "music_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users;
}