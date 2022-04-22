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

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof User)) return false;
        final User other = (User) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$telegramId = this.getTelegramId();
        final Object other$telegramId = other.getTelegramId();
        if (this$telegramId == null ? other$telegramId != null : !this$telegramId.equals(other$telegramId))
            return false;
        final Object this$firstName = this.getFirstName();
        final Object other$firstName = other.getFirstName();
        if (this$firstName == null ? other$firstName != null : !this$firstName.equals(other$firstName)) return false;
        final Object this$lastName = this.getLastName();
        final Object other$lastName = other.getLastName();
        if (this$lastName == null ? other$lastName != null : !this$lastName.equals(other$lastName)) return false;
        final Object this$userName = this.getUserName();
        final Object other$userName = other.getUserName();
        if (this$userName == null ? other$userName != null : !this$userName.equals(other$userName)) return false;
        final Object this$nickname = this.getNickname();
        final Object other$nickname = other.getNickname();
        if (this$nickname == null ? other$nickname != null : !this$nickname.equals(other$nickname)) return false;
        final Object this$cockSize = this.getCockSize();
        final Object other$cockSize = other.getCockSize();
        if (this$cockSize == null ? other$cockSize != null : !this$cockSize.equals(other$cockSize)) return false;
        final Object this$userStatus = this.getUserStatus();
        final Object other$userStatus = other.getUserStatus();
        if (this$userStatus == null ? other$userStatus != null : !this$userStatus.equals(other$userStatus))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof User;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $telegramId = this.getTelegramId();
        result = result * PRIME + ($telegramId == null ? 43 : $telegramId.hashCode());
        final Object $firstName = this.getFirstName();
        result = result * PRIME + ($firstName == null ? 43 : $firstName.hashCode());
        final Object $lastName = this.getLastName();
        result = result * PRIME + ($lastName == null ? 43 : $lastName.hashCode());
        final Object $userName = this.getUserName();
        result = result * PRIME + ($userName == null ? 43 : $userName.hashCode());
        final Object $nickname = this.getNickname();
        result = result * PRIME + ($nickname == null ? 43 : $nickname.hashCode());
        final Object $cockSize = this.getCockSize();
        result = result * PRIME + ($cockSize == null ? 43 : $cockSize.hashCode());
        final Object $userStatus = this.getUserStatus();
        result = result * PRIME + ($userStatus == null ? 43 : $userStatus.hashCode());
        return result;
    }
}