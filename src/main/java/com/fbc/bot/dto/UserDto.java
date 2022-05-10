package com.fbc.bot.dto;

import com.fbc.bot.model.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

import static com.fbc.bot.util.ValidationConstants.NOT_NULL_FIELD;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements BaseDto {

    private Long id;
    @NotNull(message = NOT_NULL_FIELD)
    private String firstName;
    @NotNull(message = NOT_NULL_FIELD)
    private String lastName;
    @NotNull(message = NOT_NULL_FIELD)
    private String userName;
    @NotNull(message = NOT_NULL_FIELD)
    private UserStatus userStatus;
    @NotNull(message = NOT_NULL_FIELD)
    private String nickname;
}