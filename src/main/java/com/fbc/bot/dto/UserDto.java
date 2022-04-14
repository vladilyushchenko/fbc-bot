package com.fbc.bot.dto;

import com.fbc.bot.model.UserStatus;
import lombok.Setter;
import lombok.Value;

import javax.validation.constraints.NotNull;

import static com.fbc.bot.util.ValidationConstants.NOT_NULL_FIELD;

@Value
@Setter
public class UserDto implements BaseDto {

    Long id;
    @NotNull(message = NOT_NULL_FIELD)
    String firstName;
    @NotNull(message = NOT_NULL_FIELD)
    String lastName;
    @NotNull(message = NOT_NULL_FIELD)
    String username;
    @NotNull(message = NOT_NULL_FIELD)
    UserStatus userStatus;
}